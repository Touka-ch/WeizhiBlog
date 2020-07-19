<template>
  <el-container style="height:100vh">
    <el-aside width="200px"><common-aside></common-aside></el-aside>
    <el-container>
      <el-header><common-header></common-header></el-header>
      <el-main><common-tab></common-tab></el-main>
    </el-container>
  </el-container>
</template>

<script>
import CommonHeader from '../components/CommonHeader'
import CommonAside from '../components/CommonAside'
//import CommonTab from '../components/CommonTab'
export default {
  name: 'Main',
  components: {
    CommonHeader,
    CommonAside
    //CommonTab
  }
}
</script>

<style lang="scss" scoped>
.el-header {
  background-color: #333;
}
</style>
