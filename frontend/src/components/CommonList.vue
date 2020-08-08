<template>
  <div>
    <el-row class="art-item" v-for="item in listData" :key="item.id">
      <el-card shadow="hover">
        <el-row class="art-item" style="font-size: 20px">
          <div class="art-title">
            <span>{{ item.title }}</span>
          </div>
        </el-row>
        <el-row style="border: 5px">
          <el-col :span="7">
            <i class="el-icon-time">
              <span>{{ item.publishDate }}</span>
            </i>
          </el-col>
          <el-col :span="17">
            <i class="el-icon-collection-tag">
              <span>：</span>
              <el-tag size="mini" style="font-size: 14px" v-for="tag in item.tags" :key="tag.id">{{ tag.tagName }}</el-tag>
            </i>
          </el-col>
        </el-row>
        <el-row class="art-body">
          <div class="side-img hidden-sm-and-down">
            <img class="art-banner" :src="getImg(item.mdContent)" :onerror="errorImg" />
            <!--:src="getImg(item.mdContent)"
            onerror="this.src='../assets/images/error.jpg'"-->
          </div>
          <div class="side-abstract">
            <div class="art-abstract">{{ item.summary }}</div>
            <div class="art-more">
              <div>
                <div v-if="isEdit">
                  <el-button type="success" @click="updateArticle(item.id)">编辑</el-button>
                  <el-button type="warning" @click="deleteArticle(item.id)">删除</el-button>
                </div>
                <div v-else>
                  <el-button @click="postId(item.id)">阅读全文</el-button>
                </div>
              </div>
              <div class="view">
                <i class="el-icon-view"></i>
                <span>{{ item.pageView }}</span>
              </div>
            </div>
          </div>
        </el-row>
      </el-card>
      <img class="star" src="../assets/images/star.png" />
    </el-row>
  </div>
</template>

<script>
import { articleRequest } from '../api/Requests'

export default {
  name: 'CommonList',
  props: {
    commonListData: {
      type: Array,
      default() {
        return [
          {
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
            tags: []
          }
        ]
      }
    }
  },
  data() {
    return {
      listData: [
        {
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
      ],
      isEdit: false,
      errorImg: 'this.src="' + require('../assets/images/error.jpg') + '"'
    }
  },
  watch: {
    commonListData(val, oldval) {
      if (val != oldval) {
        this.listData = this.commonListData
        //console.log('watch 执行了')
        this.transmit(this.listData)
      }
    },
    deep: true
  },
  methods: {
    postId(id) {
      this.$emit('postId', id)
    },
    hasUser() {
      if (JSON.parse(localStorage.getItem('user')) != null) this.isEdit = true
    },
    transmit(data) {
      for (let item of data) {
        let data = new Date(item.publishDate)
        //console.log(data.toLocaleString())
        item.publishDate = data.toLocaleString()
      }
    },
    deleteArticle(aid) {
      articleRequest('delete', aid).then(res => {
        if (res.status == '1') {
          this.$notify({ title: '成功', message: res.message, type: 'success' })
        } else this.$notify.error({ title: '失败', message: res.message })
        window.location.reload()
      })
    },
    getImg(markdown) {
      let url = markdown.replace(/.*!\[.*]\(/, '')
      let index = url.indexOf(')')
      url = url.substring(0, index)
      return url
    },
    updateArticle(aid) {
      this.$router.push({ name: 'updateArticle', params: { aid: aid } })
    }
  },
  mounted() {
    this.hasUser()
    //console.log('this.listData')
    //console.log(this.listData)
  }
}
</script>

<style scoped>
#side .item {
  margin-bottom: 30px;
}

.art-item {
  margin: 10px 0 30px 0;
  position: relative;
}

.art-item .star {
  width: 60px;
  height: 60px;
  position: absolute;
  top: 0;
  right: 0;
}

img.tag {
  width: 16px;
  height: 16px;
}

.art-title {
  border-left: 3px solid #f56c6c;
  padding-left: 5px;
  cursor: pointer;
}

.art-title:hover {
  padding-left: 10px;
  color: #409eff;
}

.art-body {
  display: flex;
  padding: 10px 0;
}

.side-img {
  height: 150px;
  width: 270px;
  overflow: hidden;
  margin-right: 10px;
}

img.art-banner {
  width: 100%;
  height: 100%;
  transition: all 0.6s;
}

img.art-banner:hover {
  transform: scale(1.4);
}

.side-abstract {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.art-abstract {
  flex: 1;
  color: #aaa;
}

.art-more {
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.art-more .view {
  color: #aaa;
}
h5 {
  font-size: 18px;
}
</style>
