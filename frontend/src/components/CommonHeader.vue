<template>
  <header>
    <div class="r-content">
      <el-dropdown trigger="click">
        <span class="el-dropdown-link"><img :src="userImg" class="user" /> </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item> 退出</el-dropdown-item>
          <el-dropdown-item>切换用户</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </header>
</template>

<script>
export default {
  name: 'CommonHeader',
  data() {
    return {
      userImg: require('..//assets/images/user (1).png')
    }
  }
}
</script>

<style lang="scss" scoped>
header {
  display: flex;
  height: 100%;
  align-items: center;
  float: right;
}
.r-content {
  .user {
    width: 50px;
    height: 50px;
  }
}
</style>
