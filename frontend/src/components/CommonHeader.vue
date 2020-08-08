<template>
  <header>
    <div class="r-content">
      <el-dropdown>
        <span class="el-dropdown-link">
          <img :src="userface" class="user" />
          <!--<el-image :src="user.userface" class="user"></el-image>-->
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>{{ user }}</el-dropdown-item>
          <el-dropdown-item>
            <div @click="login">切换用户</div>
          </el-dropdown-item>
          <el-dropdown-item>
            <div @click="logout">退出</div>
          </el-dropdown-item>
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
      userImg: require('..//assets/images/user (1).png'),
      user: '',
      userface: ''
    }
  },
  methods: {
    login() {
      localStorage.clear()
      this.$router.push({ name: 'login' })
    },
    logout() {
      //logoutRequest()
      localStorage.clear()
      this.$router.push({ name: 'index' })
    },
    getUser() {
      this.user = JSON.parse(localStorage.getItem('user')).username
      this.userface = JSON.parse(localStorage.getItem('user')).userface
    }
  },
  mounted() {
    this.getUser()
    console.log(this.userface)
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
