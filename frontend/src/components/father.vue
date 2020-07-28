<template>
  <div>
    <h1>这是father</h1>
    <h1>{{ fatherMsg }}</h1>
    <input type="button" @click="getMsg" value="这是father按钮" />
    <child @func="getMsg"></child>
    <router-link to="/home">home</router-link>
    <button @click="toTest">点我！</button>
  </div>
</template>

<script>
//@func="getMsg"
import child from './child'
export default {
  name: 'father',
  data() {
    return {
      fatherMsg: ''
    }
  },
  components: {
    child
  },
  methods: {
    getMsg(data) {
      this.fatherMsg = data
    },
    toTest() {
      this.$router.push({ path: '/test' })
    }
  }
}
</script>

<style scoped></style>
