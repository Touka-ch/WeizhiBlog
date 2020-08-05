<template>
  <div>
    <el-button @click="one">单个数据（不带括号）</el-button>
    <el-button @click="two">单个数据</el-button>
    <el-button @click="three">多个数据</el-button>
    <!--<el-button @click="four">单个标签传值</el-button> -->
  </div>
</template>
<script>
import { mulTagRequest } from '../api/Requests'

export default {
  name: 'test',
  data() {
    return {
      a: '123123132',
      b: ['123123132'],
      c: ['123123132', 'j7778789846'],
      tags: {
        tags: []
      }
    }
  },
  methods: {
    one() {
      this.tags.tags = this.a
      mulTagRequest('post', 127, this.tags).then(res => {
        console.log(res)
      })
    },
    two() {
      this.tags.tags = this.b
      mulTagRequest('post', 127, this.tags).then(res => {
        console.log(res)
      })
    },
    three() {
      this.tags.tags = this.c
      mulTagRequest('post', 127, this.tags).then(res => {
        console.log(res)
      })
    }
  }
}
</script>

<style scoped></style>
