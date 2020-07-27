import Vue from 'vue'
import VueRouter from 'vue-router'
//import Login from '../views/Login'

Vue.use(VueRouter)

const routes = [
  {
    //登录页
    path: '/login',
    name: 'login',
    component: () => import('../views/Login')
  },
  {
    path: '/main',
    component: () => import('@/views/Main.vue'),
    children: [
      {
        path: '/article',
        name: 'article',
        component: () => import('@/components/Article.vue')
      },
      {
        path: '/hub',
        name: 'hub',
        component: () => import('@/components/Hub.vue')
      },
      {
        path: '/list',
        name: 'list',
        component: () => import('@/components/List.vue')
      },
      {
        path: '/new',
        name: 'new',
        component: () => import('@/components/New.vue')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import('@/components/User.vue')
      },
      {
        path: '/column',
        name: 'column',
        component: () => import('@/components/Column.vue')
      },
      {
        path: '/chart',
        name: 'chart',
        component: () => import('@/components/Chart.vue')
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
