<template>
  <div>
    <el-container>
      <el-header>
        <h1 class="header">玮 织 博 客</h1>
        <div class="nav">
          <el-link icon="el-icon-user" @click="goback">后台登录</el-link>
          <el-link href="">关于</el-link>
          <el-link href="">友链</el-link>
          <el-link href="">统计</el-link>
          <el-link href="">归档</el-link>
          <el-link href="Index.vue">首页</el-link>
        </div>
      </el-header>
      <el-main>
        <div class="lef">
          <div class="mig">
            <el-carousel :interval="5000" arrow="always" height="400px">
              <el-carousel-item v-for="item in imgList" :key="item.id">
                <el-row>
                  <el-col :span="24"><img ref="imgHeight" :src="item.idView"/></el-col>
                </el-row>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="f"></div>
          <div class="f"></div>
          <br />
          <el-link href="" :underline="false">更多...</el-link>
        </div>
        <div class="rig">
          <p class="jj">博主简介:</p>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data() {
    return {
      imgList: [
        { id: 0, idView: require('../assets/images/usericon.jpg') },
        { id: 1, idView: require('../assets/images/2.jpg') },
        { id: 2, idView: require('../assets/images/3.jpg') },
        { id: 3, idView: require('../assets/images/4.jpg') }
      ]
    }
  },
  methods: {
    goback() {
      this.$router.push('/Login')
    }
  }
}
</script>

<style scoped>
.el-header {
  background-color: #ffffff;
  color: #333;
  /*text-align: center;*/
  line-height: 60px;
  height: 200px;
}
.header {
  margin-left: 300px;
  float: left;
  font-size: 30px;
}
.nav {
  margin-right: 50px;
}
.el-link {
  color: #33aef0;
  float: right;
  margin-right: 20px;
}
.el-main {
  background: #eeeeee;
}
.lef {
  float: left;
  height: 1200px;
  width: 50%;
  margin-left: 15%;
}
.mig {
  height: 400px;
  weidth: 100%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  line-height: 300px;
}
.f {
  height: 300px;
  weidth: 100%;
  margin-top: 20px;
  border: 1px solid #333333;
}
.rig {
  float: left;
  height: 500px;
  width: 25%;
  margin-left: 5%;
  border: 1px solid #333333;
}
.jj {
  color: #333333;
  font-size: 20px;
  margin-top: 20px;
  margin-left: 20px;
}
.a {
  text-decoration: none;
}
</style>
