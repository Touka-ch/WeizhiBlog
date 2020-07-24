<template>
  <el-container style="height:100vh">
    <el-aside :width="width + 'px'"><common-aside @collapse="getWidth"></common-aside></el-aside>
    <el-container>
      <el-header><common-header></common-header></el-header>
      <el-main><router-view /></el-main>
    </el-container>
  </el-container>
</template>

<script>
import CommonHeader from '../components/CommonHeader'
import CommonAside from '../components/CommonAside'
//import CommonTab from '../components/CommonTab'
export default {
  name: 'Main',
  computed: {},
  data() {
    return {
      width: '200'
    }
  },
  components: {
    CommonHeader,
    CommonAside

    //CommonTab
  },
  methods: {
    getWidth(isCollapse) {
      console.log(isCollapse)
      if (isCollapse) this.width = 60
      else this.width = 200
    }
  }
}

//width="200px"
</script>

<style lang="scss" scoped>
.el-header {
  background-color: #333;
}
</style>
