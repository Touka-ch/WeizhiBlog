import Vue from 'vue'
import VueRouter from 'vue-router'
//import Login from '../views/Login'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    params: 'id',
    name: 'index',
    component: () => import('../views/Index')
  },
  {
    path: '/test',
    name: 'test',
    component: () => import('../components/test')
  },
  {
    //登录页
    path: '/login',
    name: 'login',
    component: () => import('../views/Login')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/Register')
  },
  {
    path: '/main',
    component: () => import('@/views/Main.vue'),
    children: [
      {
        path: '/main/article',
        name: 'article',
        component: () => import('@/components/CommonList.vue')
      },
      {
        path: '/main/hub',
        name: 'hub',
        component: () => import('@/components/Hub.vue')
      },
      {
        path: '/main/list',
        name: 'list',
        component: () => import('@/components/List.vue')
      },
      {
        path: '/main/new',
        name: 'new',
        component: () => import('@/components/New.vue')
      },
      {
        path: '/main/user',
        name: 'user',
        component: () => import('@/components/User.vue')
      },
      {
        path: '/main/column',
        name: 'column',
        component: () => import('@/components/Column.vue')
      },
      {
        path: '/main/chart',
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
