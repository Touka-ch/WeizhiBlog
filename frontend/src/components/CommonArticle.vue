<template>
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
</template>

<script>
import { articleRequest } from '../api/Requests'

export default {
  name: 'CommonArticle',
  /*props: {
    commonArticleData: {
      type: Object,
      default() {
        return {
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
        }
      }
    }
  },*/
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
      }
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
  mounted() {
    articleRequest('get', this.$route.params.aid).then(res => {
      this.error(res.status, res.message)
      this.article = res.object
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
