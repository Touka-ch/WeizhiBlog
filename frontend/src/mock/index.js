import Mock from 'mockjs'
import homeApi from './home'
//设计延时 200-2000ms
Mock.setup({
  timeout: '200-800'
})

//拦截ajax请求
Mock.mock(/\/user\/all/, 'get', homeApi.getUser().data)
