import axios from 'axios'

//����axiosʵ��
const service = axios.create({
  //����ʱʱ��
  timeout: 3000
})

//�������������
service.interceptors.request.use(
  config => {
    return config
  },
  err => {
    console.log(err)
  }
)
//��Ӧͷ ��������������״̬��
service.interceptors.request.use(
  response => {
    let res = {}
    res.status = response.status
    res.data = response.data
    return res
  },
  err => console.log(err)
)

export default service
