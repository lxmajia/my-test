<template>
  <div class="app-container">
    <div>
      <el-form :inline="true" :model="filterAppModuleForm" class="demo-form-inline">
        <el-form-item label="AppCode">
          <el-input v-model="filterAppModuleForm.appCode" placeholder="AppCode"></el-input>
        </el-form-item>
        <el-form-item label="ModuleCode">
          <el-input v-model="filterAppModuleForm.moduleCode" placeholder="AppCode"></el-input>
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
      <el-table-column label="AppCode" width="95">
        <template slot-scope="scope">
          {{ scope.row.appCode }}
        </template>
      </el-table-column>
      <el-table-column label="ModuleCode" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.appModule }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <span>
            <el-button type="primary" @click="goSysConfig(scope.row.appCode, scope.row.id)">系统配置</el-button>
            <el-button type="primary" @click="goAppConfig(scope.row.appCode, scope.row.id)">应用配置</el-button>
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
  </div>
</template>
<script>
import {getAppModuleList} from '@/api/appmodule'
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
      listLoading: true,
      pageInfo: {
        totalCount: 0,
        pageSize: 1,
        pageNum: 10,
        pageCount: 0
      },
      filterAppModuleForm: {
        appCode: '',
        moduleCode: ''
      }
    }
  },
  created() {
    this.fetchData(1);
  },
  methods: {
    fetchData(pageNum) {
      this.listLoading = true
      let queryParam = {
        pageNum: pageNum,
        pageSize: 10,
        appCode: this.filterAppModuleForm.appCode,
        moduleCode: this.filterAppModuleForm.moduleCode
      }

      getAppModuleList(queryParam).then(response => {
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
    goSysConfig(appCode, moduleId){
      console.log(appCode + " - " + moduleId)

      // 设置参数
      const params = {
        appCode: appCode,
        moduleId: moduleId
      };
      // 使用 $router 进行跳转并设置参数
      this.$router.push({
        path: '/sysconfig/index',
        query: params  // 设置查询参数
      });
    },
    goAppConfig(appCode, moduleId){
      console.log(appCode + " - " + moduleId)

      // 设置参数
      const params = {
        appCode: appCode,
        moduleId: moduleId
      };
      // 使用 $router 进行跳转并设置参数
      this.$router.push({
        path: '/configinfo/index',
        query: params  // 设置查询参数
      });
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
