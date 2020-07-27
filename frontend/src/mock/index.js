import Mock from 'mockjs'
import somApi from './someApi'
//设计延时 200-2000ms
Mock.setup({
  timeout: '200-800'
})

//拦截ajax请求
Mock.mock(/\/user\/all/, 'get', somApi.getUser())
