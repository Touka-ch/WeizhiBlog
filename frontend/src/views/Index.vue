<template>
  <el-container>
    <el-header>
      <h1 class="header">玮 织 博 客</h1>
      <div class="nav">
        <el-link icon="el-icon-user" @click="goBack">后台登录</el-link>
        <el-link href="">关于</el-link>
        <el-link href="">友链</el-link>
        <el-link href="">统计</el-link>
        <el-link href="">归档</el-link>
        <el-link href="Index.vue">首页</el-link>
      </div>
    </el-header>
    <el-main class="el-main">
      <div style="width: 70%; margin:10px auto 30px auto">
        <el-carousel :interval="5000" height="300px">
          <el-carousel-item v-for="item in imgList" :key="item.id">
            <el-image fit="fill" :src="item.idView" />
          </el-carousel-item>
        </el-carousel>
      </div>
      <div style="width: 85%; margin:10px auto 30px auto">
        <el-row :gutter="40">
          <!--间隔距离-->
          <el-col :span="16">
            <div class="blog">
              <common-list v-if="isDisplay" :common-list-data="articles" @postId="goRouter"> </common-list>
              <!--@postId="goRouter"-->
              <router-view></router-view>
            </div>
          </el-col>
          <el-col :span="8">
            <div>
              <!--<common-category></common-category>-->
            </div>
            <el-card class="box-card">
              <div slot="header" class="d-flex align-items-center">
                <img class="card-icon" src="src/assets/images/lianjie.png" />
                <span>为空</span>
                <el-button class="ml-auto" style="padding: 3px 0" type="text"> </el-button>
              </div>
              <div class="text item"><a href="#">某某的博客</a></div>
              <div class="text item"><a href="#">某某的博客</a></div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import CommonList from '../components/CommonList'
//import CommonCategory from '../components/CommonCategory'
import { articleRequest } from '../api/Requests'
export default {
  name: 'Index',
  components: { CommonList }, //CommonCategory,
  data() {
    return {
      imgList: [
        { id: 0, idView: require('../assets/images/1.jpg') },
        { id: 1, idView: require('../assets/images/2.jpg') },
        { id: 2, idView: require('../assets/images/3.jpg') },
        { id: 3, idView: require('../assets/images/4.jpg') }
      ],
      isDisplay: true,
      id: '',
      flag: false,
      articles: []
    }
  },
  methods: {
    goBack() {
      this.$router.push({ name: 'login' })
    },
    goRouter(id) {
      this.id = id
      this.isDisplay = false
      //console.log(id)
      this.$router.push({ name: 'article', params: { aid: id } })
    },
    isError(status, message) {
      if (status == '1') this.$message({ message: message, type: 'success' })
    }
  },
  created() {
    articleRequest('get').then(res => {
      this.isError(res.status, res.message)
      //console.log(res.object)
      this.articles = res.object
    })
    if (this.$route.name === 'article') {
      this.isDisplay = false
    }
  },
  mounted() {
    localStorage.clear()
  }
}
</script>

<style scoped>
.el-header {
  background-color: #ffffff;
  color: #333;
  line-height: 60px;
}
.header {
  margin-left: 15%;
  float: left;
  font-size: 30px;
  position: relative;
}
.nav {
  margin-right: 3%;
}
.el-link {
  color: #33aef0;
  float: right;
  margin-right: 20px;
}
.el-main {
  background: #eeeeee;
}
</style>
