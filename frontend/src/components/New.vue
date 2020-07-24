<template>
  <mavon-editor v-model="context" />
</template>

<script>
export default {
  name: 'New',
  data() {
    return {
      context: ' ', //输入的数据
      html: ''
    }
  }
}
</script>

<style scoped></style>
