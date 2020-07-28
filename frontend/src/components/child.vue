<template>
  <input type="button" @click="sendMsg" value="这是child按钮" />
</template>

<script>
export default {
  name: 'child',
  date() {
    return {
      msg: 'true'
    }
  },
  methods: {
    sendMsg() {
      this.$emit('func', '123456')
      console.log(this.msg)
      console.log('123123')
    }
  }
}
</script>

<style scoped></style>
