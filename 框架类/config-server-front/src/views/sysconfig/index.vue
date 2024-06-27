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
      <el-table-column label="ConfigKey" width="400">
        <template slot-scope="scope">
          {{ scope.row.configKey }}
        </template>
      </el-table-column>
      <el-table-column label="ConfigValue" width="200" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.configValue }}</span>
        </template>
      </el-table-column>
      <el-table-column label="CreateTime" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <span>操作 | 操作</span>
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
  </div>
</template>
<script>
import {getAppModuleStructList, getSysConfig} from '@/api/sysconfig'
import {Message, Pagination, Form} from "element-ui";

export default {
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
        configKey:""
      }
    }
  },
  created() {
    // 使用 $route 获取传递的参数
    this.initAppModuleStructData();
    if(this.$route.query.appCode){
      this.filterAppModuleForm.appCode = this.$route.query.appCode;
      this.changeAppCode(this.filterAppModuleForm.appCode);
    }
    if(this.$route.query.moduleId){
      this.filterAppModuleForm.appModuleCodeId = this.$route.query.moduleId;
      this.fetchData(1);
    }
  },
  methods: {
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
    changeAppCode(appCode){
      this.moduleList = this.appModuleMapping[appCode];
    },
    refreshModuleSelect(){
      this.$forceUpdate();
    },
    fetchData(pageNum) {
      if(!this.filterAppModuleForm.appModuleCodeId){
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

      getSysConfig(queryParam).then(response => {
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
