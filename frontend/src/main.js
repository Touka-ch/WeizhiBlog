import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router'
import store from './store'
import './assets/scss/reset.scss'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import http from './api/config.js'
import './mock'

// 安装路由
Vue.use(VueRouter)
//第三方组件
Vue.use(mavonEditor)
Vue.use(ElementUI)
//调用 axios 实例
Vue.prototype.$http = http

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
