<template>
  <!--<mavon-editor v-model="context" @save="saveDoc" @change="updateDoc" />-->
  <div>
    <div>题目：<el-input></el-input></div>
    <mavon-editor v-model="context" @save="saveDoc" @change="updateDoc"></mavon-editor>
  </div>
</template>

<script>
export default {
  name: 'New',
  data() {
    return {
      context: '', //输入的数据
      html: ''
    }
  },
  methods: {
    updateDoc(markdown, html) {
      // 此时会自动将 markdown 和 html 传递到这个方法中
      console.log('markdown内容: ' + markdown)
      console.log('html内容:' + html)
    },
    saveDoc(markdown, html) {
      // 此时会自动将 markdown 和 html 传递到这个方法中
      console.log('markdown内容:' + markdown)
      console.log('html内容:' + html)
    }
  }
}
</script>

<style scoped></style>
