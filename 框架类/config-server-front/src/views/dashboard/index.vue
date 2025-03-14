<template>
  <div class="dashboard-container">
    <div class="dashboard-text">登录用户: {{ name }}</div>
    <div class="dashboard-text">应用管理: 配置哪些应用接入了配置中心</div>
    <div class="dashboard-text">系统配置: 项目启动时会加载进系统变量（如Mysql连接，Redis连接等）</div>
    <div class="dashboard-text">应用配置: 会实时更新的配置，可以在项目中用于切换新老流程，配置业务开关等</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
