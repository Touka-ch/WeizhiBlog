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
    <comment :comments-data="comments" :props-aid="aid"></comment>
  </div>
</template>

<script>
import { articleRequest, commentArticleRequest } from '../api/Requests'
import Comment from './Comment'

export default {
  name: 'CommonArticle',
  components: { Comment },
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
      comments: [],
      oldComments: [],
      fakeComments: [
        {
          content: '评论示例',
          date: '2020/8/8 上午 9:01:46', //1596768890000,
          fromAvatar: 'http://47.115.41.198:8090/public/2020/08/07/03/43/27/270/touxiang5.png',
          fromId: 12,
          fromName: 'user',
          id: 27,
          likeNum: 1,
          ownerId: 28,
          toId: -1
        },
        {
          content: '回复示例',
          date: '2020/8/8 下午 1:01:46', //1596768894000,
          fromAvatar: 'http://47.115.41.198:8090/public/2020/08/07/03/43/27/270/touxiang5.png',
          fromId: 12,
          fromName: 'user',
          id: 28,
          likeNum: 1,
          ownerId: 28,
          toId: 27
        }
      ],
      aid: ''
    }
  },
  methods: {
    error(status, message) {
      if (status == '1') this.$message({ message: message, type: 'success' })
    },
    goback() {
      this.$router.push({ name: 'index', params: { view: 'yes' } })
      window.location.reload()
    },
    rawToFit(comments) {
      let fitComments = []
      let comments1 = comments.sort(this.compare('toId'))
      console.log('第一次排序后的评论：' + comments1)
      for (let i = 0; i < comments1.length; i++) {
        if (comments1[i]['toId'] === -1) {
          fitComments.push(comments1[i])
        }
      }
      for (let i = 0; i < comments1.length; i++) {
        if (comments1[i]['toId'] != -1) {
          for (let j = 0; j < fitComments.length; j++) {
            if (fitComments[j]['reply'] === undefined || fitComments[j]['reply'].length === 0) {
              fitComments[j]['reply'] = []
            }
            if (fitComments[j]['id'] === comments1[i]['toId']) {
              fitComments[j]['reply'].push(comments1[i])
            }
          }
        }
      }
      return fitComments
    },
    compare(property) {
      return function(obj1, obj2) {
        let value1 = obj1[property]
        let value2 = obj2[property]
        return value1 - value2 // 升序
      }
    }
  },
  created() {
    this.aid = this.$route.params.aid
    //console.log(this.aid)
    articleRequest('get', this.aid).then(res => {
      this.error(res.status, res.message)
      this.article = res.object
    })
    commentArticleRequest('get', this.aid).then(res => {
      //console.log(res)
      this.error(res.status, res.message)
      this.oldComments = res.object
      if (this.oldComments == null) {
        this.comments = this.rawToFit(this.oldComments)
      } else {
        this.comments = this.rawToFit(this.fakeComments)
      }
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
