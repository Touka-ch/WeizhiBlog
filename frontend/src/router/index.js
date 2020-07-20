import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('@views/Index.vue')
  },
  /*{
    //登录页
    path: '/',
    name: 'Login',
    component: Login
  },*/
  {
    path: '/main',
    component: () => import('@/views/Main.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
