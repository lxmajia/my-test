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
                         path="res" :data="formatConfigValueDialogValue" > </vue-json-pretty>
      </span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="formatConfigValueDialogShow = false">复 制</el-button>
    <el-button type="primary" @click="formatConfigValueDialogShow = false">关 闭</el-button>
  </span>
    </el-dialog>
  </div>

</template>


<script>
import {getConfigInfo} from '@/api/configinfo'
import {getAppModuleStructList} from '@/api/appmodule'
import {Message, Pagination, Form} from "element-ui";
import VueJsonPretty from 'vue-json-pretty';
import 'vue-json-pretty/lib/styles.css';

export default {
  components : {VueJsonPretty},
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
      listLoading: false,
      moduleList: [],
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
      }
    }
  },
  created() {
    // 使用 $route 获取传递的参数
    this.initAppModuleStructData();
    if (this.$route.query.appCode) {
      this.filterAppModuleForm.appCode = this.$route.query.appCode;
      this.changeAppCode(this.filterAppModuleForm.appCode);
    }
    if (this.$route.query.moduleId) {
      this.filterAppModuleForm.appModuleCodeId = this.$route.query.moduleId;
      this.fetchData(1);
    }
  },
  methods: {
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
