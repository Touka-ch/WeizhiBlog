<template>
  <div>
    <common-list :common-list-data="articles"></common-list>
    <router-view></router-view>
  </div>
</template>

<script>
import CommonList from './CommonList'
import { articleUserRequest } from '../api/Requests'
export default {
  name: 'List',
  components: { CommonList },
  data() {
    return {
      articles: []
    }
  },
  methods: {
    isError(status, message) {
      if (status == '1') this.$message({ message: message, type: 'success' })
    }
  },
  created() {
    articleUserRequest(JSON.parse(localStorage.getItem('user')).id).then(res => {
      this.isError(res.status, res.message)
      this.articles = res.object
    })
  },
  mounted() {}
}
</script>

<style scoped></style>
