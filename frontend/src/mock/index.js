import Mock from 'mockjs'
import somApi from './someApi'
//�����ʱ 200-2000ms
Mock.setup({
  timeout: '200-800'
})

//����ajax����
Mock.mock(/\/user\/all/, 'get', somApi.getUser())
