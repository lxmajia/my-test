<template>
  <div class="app-container">
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
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="AppCode"  width="95">
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
          <span>操作 | 操作</span>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="1000">
    </el-pagination>
  </div>
</template>

<script>
import {getAppModuleList} from '@/api/appmodule'
import {Message, Pagination} from "element-ui";

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
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getAppModuleList().then(response => {
        console.log(response)
        const {data} = response;
        this.listLoading = false
        if(data.code === 0){
          this.list = data.body.list
        }else{
          Message({
            message: data.message,
            type: 'error',
            duration: 5 * 1000
          })
        }
      })
    }
  }
}
</script>
