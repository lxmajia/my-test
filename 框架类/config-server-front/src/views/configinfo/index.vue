<template>
  <div class="app-container">
    <div>
      <el-form :inline="true" :model="filterAppModuleForm" class="demo-form-inline">
        <el-form-item label="AppCode">
          <el-select v-model="filterAppModuleForm.appCode" placeholder="AppCode" @change="changeAppCode">
            <el-option v-for="item in appList" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="ModuleCode">
          <el-select v-model="filterAppModuleForm.appModuleCodeId" placeholder="ModuleCode" @change="refreshModuleSelect">
            <el-option v-for="item in moduleList" :key="item.moduleId" :label="item.moduleCode" :value="item.moduleId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="ModuleCode">
          <el-input v-model="filterAppModuleForm.configKey" placeholder="ConfigKey"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="querySearch">查询</el-button>
          <el-button type="primary" @click="addConfigInfo">新增</el-button>
        </el-form-item>
      </el-form>
    </div>


    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="ConfigKey" width="250">
        <template slot-scope="scope">
          {{ scope.row.configKey }}
        </template>
      </el-table-column>
      <el-table-column label="ConfigValue" width="450" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ scope.row.configValue }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" width="180" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.modified }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <span>
            <el-button type="primary" @click="showFormatConfigValueDialog(scope.row.configValue)">格式化展示</el-button>
            <el-button type="primary"
                       @click="editConfigInfo(scope.row.id,scope.row.appModuleId,scope.row.configKey,scope.row.configValue)">编辑</el-button>
            <el-button type="primary" @click="deleteConfigInfo()">删除</el-button>
          </span>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination
        background
        layout="->,prev, pager, next"
        :page-size="pageInfo.pageSize"
        :page-count="pageInfo.pageCount"
        :current-page="pageInfo.pageNum"
        :total="pageInfo.totalCount"
        @current-change="changePageNum">
      </el-pagination>
    </div>


    <el-dialog
      title="提示"
      :visible.sync="formatConfigValueDialogShow"
      width="60%">
      <span>
        <vue-json-pretty :deep="10" selectableType="single" :showSelectController="true" :highlightMouseoverNode="true"
                         path="res" :data="formatConfigValueDialogValue"> </vue-json-pretty>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="formatConfigValueDialogShow = false">复 制</el-button>
        <el-button type="primary" @click="formatConfigValueDialogShow = false">关 闭</el-button>
      </span>
    </el-dialog>


    <el-dialog title="配置 修改/新增" :visible.sync="configInfoEditOrAddDialogForm.showConfigInfoEditOrAddDialog">
      <el-form :model="configInfoEditOrAddDialogForm">
        <el-form-item label="归属" label-width="200">
          <el-input v-model="configInfoEditOrAddDialogForm.belong" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="ConfigKey" label-width="200">
          <el-input v-model="configInfoEditOrAddDialogForm.configKey" :disabled="configInfoEditOrAddDialogForm.id > 0" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="ConfigValue" label-width="200">
          <el-tag key="OK" :type="configInfoEditOrAddCheckTag.tagType" effect="dark" size="small">{{configInfoEditOrAddCheckTag.tagLabel}}</el-tag>
          <el-input
            @input="checkConfigValueIsJson"
            type="textarea"
            autosize
            placeholder="请输入内容(标准JSON格式)"
            :autosize="{ minRows: 6}"
            v-model="configInfoEditOrAddDialogForm.configValue">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="formatEditConfigValue">格式化</el-button>
        <el-button @click="configInfoEditOrAddDialogForm.showConfigInfoEditOrAddDialog = false">取 消</el-button>
        <el-button type="primary" @click="submitEditOrAddConfigInfo">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>


<script>
import {getConfigInfo, updateConfigInfo} from '@/api/configinfo'
import {getAppModuleStructList} from '@/api/appmodule'
import {Message} from "element-ui";
import VueJsonPretty from 'vue-json-pretty';
import 'vue-json-pretty/lib/styles.css';

export default {
  components: {VueJsonPretty},
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      formatConfigValueDialogShow: false,
      formatConfigValueDialogValue: "",
      list: null,
      appList: [],
      moduleList: [],
      listLoading: false,
      appModuleMapping: {},
      pageInfo: {
        totalCount: 0,
        pageSize: 1,
        pageNum: 10,
        pageCount: 0
      },
      filterAppModuleForm: {
        appCode: "",
        appModuleCodeId: undefined,
        configKey: ""
      },
      configInfoEditOrAddCheckTag: {
        tagType: "success",
        tagLabel: "校验通过"
      },
      configInfoEditOrAddDialogForm: {
        showConfigInfoEditOrAddDialog: false,
        id: null,
        appModuleId: null,
        belong: "",
        configKey: "",
        configValue: ""
      }
    }
  },
  created() {
    // 使用 $route 获取传递的参数
    if (this.$route.query.appCode) {
      this.filterAppModuleForm.appCode = this.$route.query.appCode;
    }
    if (this.$route.query.moduleId) {
      this.filterAppModuleForm.appModuleCodeId = parseInt(this.$route.query.moduleId, 10);
      this.fetchData(1);
    }
    this.initAppModuleStructData();
  },
  methods: {
    formatEditConfigValue() {
      let configValue = this.configInfoEditOrAddDialogForm.configValue;
      if (/^\s*$/.test(configValue)) {
        Message({
          message: "数据为空，无法格式化",
          type: 'error',
          duration: 5 * 1000
        })
        return;
      }
      try {
        let data = JSON.parse(configValue);
        this.configInfoEditOrAddDialogForm.configValue = JSON.stringify(data, null, 2);
      } catch (e) {
        Message({
          message: "数据格式错误，请输入标准JSONObject格式",
          type: 'error',
          duration: 5 * 1000
        })
        return configValue;
      }
    },
    checkConfigValueIsJson() {
      let configValue = this.configInfoEditOrAddDialogForm.configValue;
      try {
        JSON.stringify(JSON.parse(configValue));
        this.configInfoEditOrAddCheckTag = {
          tagType: "success",
          tagLabel: "校验通过"
        }
        return true;
      } catch (e) {
        this.configInfoEditOrAddCheckTag = {
          tagType: "danger",
          tagLabel: "校验失败"
        }
        return false;
      }
    },
    editConfigInfo(id, appModuleId, configKey, configValue) {
      let belong = "修改_" + id;
      this.configInfoEditOrAddDialogForm = {
        showConfigInfoEditOrAddDialog: true,
        belong: belong,
        id: id,
        appModuleId: appModuleId,
        configKey: configKey,
        configValue: configValue
      };
    },
    addConfigInfo() {
      if (!this.filterAppModuleForm.appModuleCodeId) {
        Message({
          message: "过滤窗口先选择应用",
          type: 'error',
          duration: 5 * 1000
        })
        return;
      }
      let belong = "新增";
      this.configInfoEditOrAddDialogForm = {
        showConfigInfoEditOrAddDialog: true,
        belong: belong,
        id: null,
        appModuleId: this.filterAppModuleForm.appModuleCodeId,
        configKey: "",
        configValue: ""
      };
    },
    submitEditOrAddConfigInfo() {
      let checkConfigValueIsJson1 = this.checkConfigValueIsJson();
      if(!checkConfigValueIsJson1){
        Message({
          message: "ConfigValue非JSON对象格式，请修改",
          type: 'error',
          duration: 5 * 1000
        })
        return;
      }

      let configInfoEditOrAddDialogForm = this.configInfoEditOrAddDialogForm;

      // 将JSON压缩存储，压缩失败就用原来的
      let newConfigValue = configInfoEditOrAddDialogForm.configValue;
      try {
        let data = JSON.parse(configInfoEditOrAddDialogForm.configValue);
        newConfigValue = JSON.stringify(data);
      } catch (e) {
        newConfigValue = configInfoEditOrAddDialogForm.configValue;
      }
      let submitForm = {
        "id": configInfoEditOrAddDialogForm.id,
        "appModuleId": configInfoEditOrAddDialogForm.appModuleId,
        "configKey": configInfoEditOrAddDialogForm.configKey,
        "configValue": newConfigValue
      };

      updateConfigInfo(submitForm).then(response => {
        this.configInfoEditOrAddDialogForm.showConfigInfoEditOrAddDialog = false;
        const {data} = response;
        if (data.code === 0) {
          Message({
            message: "操作成功，自动刷新该页面",
            type: 'success',
            duration: 5 * 1000
          })
          this.fetchData(this.pageInfo.pageNum);
        } else {
          Message({
            message: data.message,
            type: 'error',
            duration: 5 * 1000
          })
        }
      })
    },
    deleteConfigInfo() {
      Message({
        message: "暂不开放使用，影响核心系统启动",
        type: 'error',
        duration: 5 * 1000
      })
    },
    showFormatConfigValueDialog(value) {
      this.formatConfigValueDialogValue = JSON.parse(value);
      this.formatConfigValueDialogShow = true;
    },

    initAppModuleStructData() {
      getAppModuleStructList().then(response => {
        const {data} = response;
        if (data.code === 0) {
          let appModuleListMapping = data.body;
          let appList = [];
          this.appModuleMapping = appModuleListMapping;
          for (let key in appModuleListMapping) {
            appList.push(key);
          }
          this.appList = appList;

          if (this.filterAppModuleForm.appCode) {
            this.changeAppCode(this.filterAppModuleForm.appCode);
          }
        }
      });
    },
    changeAppCode(appCode) {
      this.moduleList = this.appModuleMapping[appCode];
    },
    refreshModuleSelect() {
      this.$forceUpdate();
    },
    fetchData(pageNum) {
      if (!this.filterAppModuleForm.appModuleCodeId) {
        Message({
          message: '选择moduleCode',
          type: 'error',
          duration: 5 * 1000
        })
        return;
      }
      this.listLoading = true

      let queryParam = {
        pageNum: pageNum,
        pageSize: 10,
        appModuleId: this.filterAppModuleForm.appModuleCodeId,
        configKey: this.filterAppModuleForm.configKey
      }

      getConfigInfo(queryParam).then(response => {
        const {data} = response;
        this.listLoading = false
        if (data.code === 0) {
          this.list = data.body.list
          this.pageInfo = {
            totalCount: data.body.total,
            pageSize: data.body.pageSize,
            pageNum: data.body.pageNum,
            pageCount: data.body.size
          };
        } else {
          Message({
            message: data.message,
            type: 'error',
            duration: 5 * 1000
          })
        }
      })
    },
    // 页码变了，其他条件都没变
    changePageNum(pageSize) {
      this.fetchData(pageSize);
    },
    querySearch() {
      this.fetchData(1);
    }
  }
}
</script>
