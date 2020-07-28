//import Mock from 'mockjs'

const array = [
  {
    status: 0,
    message: '未知错误！'
  },
  {
    status: 1,
    message: '获取成功',
    object: [
      {
        id: 6,
        username: 'linghu',
        nickname: '令狐葱',
        password: '202cb962ac59075b964b07152d234b70',
        enabled: true,
        email: 'linghu@qq.com',
        userface:
          'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920326&di=44a6fa6b597d86f475c2b15fa93008dd&imgtype=0&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2015-01-12%2F023019564.jpg',
        regTime: '2017-12-08T09:30:22.000+00:00'
      },
      {
        id: 7,
        username: 'sang',
        nickname: '江南一点雨',
        password: '202cb962ac59075b964b07152d234b70',
        enabled: true,
        email: 'sang123@qq.com',
        userface:
          'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg',
        regTime: '2017-12-21T13:30:29.000+00:00'
      },
      {
        id: 10,
        username: 'qiaofeng',
        nickname: '乔峰',
        password: '202cb962ac59075b964b07152d234b70',
        enabled: true,
        email: 'qiaofeng@qq.com',
        userface:
          'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg',
        regTime: '2017-12-24T06:30:46.000+00:00'
      },
      {
        id: 13,
        username: 'duanzhengchun',
        nickname: '段正淳',
        password: '202cb962ac59075b964b07152d234b70',
        enabled: false,
        email: 'duanzhengchun@qq.com',
        userface:
          'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg',
        regTime: '2017-12-24T06:30:46.000+00:00'
      }
    ]
  }
]

export default {
  getUser: () => {
    return {
      data: array[Math.floor(Math.random() * 2)]
    }
  }
}
