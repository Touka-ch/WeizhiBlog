<template>
  <!--<mavon-editor v-model="context" @save="saveDoc" @change="updateDoc" />-->
  <div>
    <el-row>
      <el-col :span="18">
        <el-input placeholder="请输入标题" v-model="article.title">
          <template slot="prepend">标题</template>
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="danger" @click="publish">发表文章</el-button>
      </el-col>
    </el-row>
    <el-row>
      <mavon-editor v-model="article.mdContent" @save="saveDoc" @change="updateDoc"></mavon-editor>
    </el-row>
    <el-row>
      <el-col :span="20">
        <el-select v-model="article.cid" placeholder="请选择要保存的目录" style="width:300px">
          <el-option v-for="item in categories" :key="item.id" :label="item.cateName" :value="item.id"> </el-option>
        </el-select>
      </el-col>
    </el-row>
    <el-row>
      <el-tag :key="tag" v-for="tag in dynamicTags" closable :disable-transitions="false" @close="handleClose(tag)">
        {{ tag }}
      </el-tag>
      <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="small"
        @keyup.enter.native="handleInputConfirm"
        @blur="handleInputConfirm"
      >
      </el-input>
      <el-button v-else class="button-new-tag" size="small" @click="showInput">点击添加您想要的标签</el-button>
    </el-row>
  </div>
</template>

<script>
import { articleRequest, categoryUserRequest, mulTagRequest } from '../api/Requests' //articleRequest,

export default {
  name: 'New',
  data() {
    return {
      article: {
        title: '',
        mdContext: '', //输入的数据
        htmlContent: '',
        summary: '',
        cid: '',
        uid: '',
        state: '1',
        publishDate: '',
        publicToOthers: true
      },
      aid: '',
      categories: [],
      user: {},
      value: '',
      dynamicTags: [],
      tags: { tags: [] },
      inputVisible: false,
      inputValue: ''
    }
  },
  /*watch: {
    categories(val, oldval) {
      if (val != oldval) this.categories
    }
  },*/
  watch: {
    aid(val, oldval) {
      if (val != oldval) {
        mulTagRequest(this.aid, this.tags).then(res => {
          if (res.status == '1') {
            this.$notify({ title: '成功', message: res.message, type: 'success' })
          } else {
            this.$notify.error({ title: '失败', message: res.message })
          }
        })
      }
    }
  },
  methods: {
    updateDoc(markdown, html) {
      // 此时会自动将 markdown 和 html 传递到这个方法中
      //console.log('markdown内容: ' + markdown)
      //console.log('html内容:' + html)
      this.summary(html)
    },
    saveDoc() {
      // 此时会自动将 markdown 和 html 传递到这个方法中
      //console.log('markdown内容:' + markdown)
      //console.log('html内容:' + html)
      this.publish()
    },
    summary(html) {
      this.article.summary = html
        .replace(/<(style|script|iframe)[^>]*?>[\s\S]+?<\/\1\s*>/gi, '')
        .replace(/<[^>]+?>/g, '')
        .replace(/\s+/g, ' ')
        .replace(/ /g, ' ')
        .replace(/>/g, ' ')
      this.article.summary = this.article.summary.substring(0, 150)
    },
    publish() {
      this.tags.tags = this.dynamicTags
      articleRequest('post', '', this.article).then(res => {
        console.log(res)
        this.aid = res.object.id
        if (res.status == '1') {
          this.$notify({ title: '成功', message: res.message, type: 'success' })
        } else {
          this.$notify.error({ title: '失败', message: res.message })
        }
      })
    },
    error(status, message) {
      if (status != '1') this.$message.error({ message: message })
    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
    },
    showInput() {
      this.inputVisible = true
      // eslint-disable-next-line no-unused-vars
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
        console.log(this.dynamicTags)
      })
    },
    handleInputConfirm() {
      let inputValue = this.inputValue
      if (inputValue) {
        this.dynamicTags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    }
  },
  mounted() {
    this.user = JSON.parse(localStorage.getItem('user'))
    this.article.uid = this.user.id
    categoryUserRequest(this.user.id).then(res => {
      console.log(res)
      this.error(res.status, res.message)
      this.categories = res.object
    })
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 15px;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
