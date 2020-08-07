<template>
  <div>
    <el-card calss="el-card">
      <el-row>
        <div class="el-icon-arrow-left" @click="goback">返回</div>
      </el-row>
      <el-row>
        <div class="title">{{ article.title }}</div>
      </el-row>
      <el-row>
        <div style="text-align: center">
          <i class="el-icon-collection-tag">
            <span>：</span>
            <el-tag size="mini" style="font-size: 14px" v-for="tag in article.tags" :key="tag.id">{{ tag.tagName }}</el-tag>
          </i>
          <i class="el-icon-time">
            <span>{{ article.publishDate }}</span>
          </i>
          <i class="el-icon-view"></i>
          <span>{{ article.pageView }}</span>
        </div>
      </el-row>
      <el-row>
        <!--<div v-html="article.data"></div>-->
        <mavon-editor
          :value="article.mdContent"
          :subfield="false"
          :defaultOpen="'preview'"
          :toolbarsFlag="false"
          :editable="false"
          :scrollStyle="true"
          :ishljs="true"
          :boxShadow="false"
        ></mavon-editor>
      </el-row>
    </el-card>
    <!---<comment :comments-data="comments"></comment>-->
  </div>
</template>

<script>
import { articleRequest, commentArticleRequest } from '../api/Requests'
//import Comment from './Comment'

export default {
  name: 'CommonArticle',
  components: {}, // Comment
  data() {
    return {
      content: '# your markdown content',
      article: {
        id: '',
        title: '',
        mdContent: '',
        htmlContent: '',
        summary: '',
        cid: '',
        uid: '',
        publishDate: '',
        editTime: '',
        commentNum: '',
        likeNum: '',
        State: '',
        pageView: '',
        publicToOthers: '',
        tags: [
          {
            id: '',
            tagName: ''
          }
        ]
      },
      comments: {}
    }
  },
  methods: {
    error(status, message) {
      if (status == '1') this.$message({ message: message, type: 'success' })
    },
    goback() {
      this.$router.push({ name: 'index', params: { view: 'yes' } })
      window.location.reload()
    }
  },
  created() {
    articleRequest('get', this.$route.params.aid).then(res => {
      this.error(res.status, res.message)
      this.article = res.object
    })
    commentArticleRequest('get', this.$route.params.aid).then(res => {
      console.log(res)
      this.error(res.status, res.message)
      this.comments = res.object
    })
  }
}
</script>

<style scoped>
.title {
  text-align: center;
  font-size: 30px;
  font-family: 'Microsoft YaHei';
  font-weight: bold;
  margin-bottom: 60px;
}
.el-card {
  margin: 10px 0 30px 0;
  position: relative;
}
</style>
