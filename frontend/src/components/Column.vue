<template>
  <common-category :common-category="categories"></common-category>
</template>

<script>
import CommonCategory from './CommonCategory'
import { categoryUserRequest } from '../api/Requests'
export default {
  name: 'Column',
  components: { CommonCategory },
  data() {
    return {
      userId: '',
      categories: []
    }
  },
  methods: {
    error(status, message) {
      if (status == 1) this.$message({ message: message, type: 'success' })
    }
  },
  mounted() {
    this.userID = JSON.parse(localStorage.getItem('user')).id
    categoryUserRequest(this.userID).then(res => {
      //console.log(res)
      this.error(res.status, res.message)
      this.categories = res.object
    })
  }
}
</script>

<style scoped></style>
