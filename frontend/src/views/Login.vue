<template>
  <div>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h2 class="login-title">玮织博客用户登录</h2>
      <el-form-item label="账号" prop="username">
        <el-input type="text" placeholder="请输入账号" v-model="form.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" placeholder="请输入密码" v-model="form.password" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" @keyup.enter.native="onSubmit">登录</el-button>
      </el-form-item>
      <el-form-item label="没有账号，">
        <el-button type="text" @click="register">立即注册</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="温馨提示" :visible.sync="dialogVisible" width="30%">
      <!--:before-close="handleClose"-->
      <span>请输入账号和密码</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { loginRequest } from '../api/Requests'

export default {
  name: 'Login',
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      // 表单验证，需要在 el-form-item 元素中增加 prop 属性
      rules: {
        username: [{ required: true, message: '账号不可为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不可为空', trigger: 'blur' }]
      },
      // 对话框显示和隐藏
      dialogVisible: false,
      register1: '',
      data: '',
      data2: ''
    }
  },
  methods: {
    onSubmit() {
      // 为表单绑定验证功能
      loginRequest(this.form).then(res => {
        if (res.status == '1') {
          //console.log(res)
          localStorage.setItem('user', JSON.stringify(res.object))
          this.$notify({ title: '成功', message: res.message, type: 'success' })
          //console.log(JSON.parse(localStorage.getItem('user')))
          //console.log(localStorage.getItem('user'))
          this.$router.push('/main')
        } else {
          this.$notify({ title: '失败', message: res.message, type: 'error' })
          //this.dialogVisible = true
        }
      })
    },
    /*
      if (valid) {
        // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
        this.$router.push('/main')
      } else {
        this.dialogVisible = true
        return false
      }*/
    register() {
      this.$router.push('/register')
    }
  },
  created() {}
}
</script>

<style lang="scss" scoped>
.login-box {
  border: 1px solid #dcdfe6;
  width: 350px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}

.login-title {
  font-size: 20px;
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>
