<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login-box">
    <h2 class="login-title">注册新用户</h2>
    <el-form-item label="用户名" prop="username">
      <el-input placeholder="不少于3个字符" v-model="ruleForm.username" @change="disable"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="nickname">
      <el-input placeholder="想个好听的名字吧!" v-model="ruleForm.nickname" @change="disable"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" placeholder="不少于3个字符" v-model="ruleForm.password" @change="disable"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPwd">
      <el-input type="password" placeholder="再输入一次吧" v-model="ruleForm.checkPwd" @change="disable"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input placeholder="请输入常用的邮箱" v-model="ruleForm.email" @change="disable"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleForm)" :disabled="flag">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { userRequest } from '../api/Requests'
export default {
  name: 'register',
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPwd !== '') {
          this.$refs.ruleForm.validateField('checkPwd')
        }
        callback()
      }
    }
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      flag: true,
      ruleForm: {
        username: '',
        password: '',
        checkPwd: '',
        nickname: '',
        email: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
        password: [{ required: true, validator: validatePass, trigger: 'blur' }],
        checkPwd: [{ required: true, validator: validatePass2, trigger: 'blur' }],
        email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }]
      }
    }
  },
  watch: {},
  methods: {
    disable() {
      if (
        this.ruleForm.username == '' ||
        this.ruleForm.password == '' ||
        this.ruleForm.checkPwd == '' ||
        this.ruleForm.nickname == '' ||
        this.ruleForm.email == ''
      ) {
        this.flag = true
      } else {
        this.flag = false
      }
    },
    submitForm: function(formName) {
      console.log(formName)
      userRequest('post', '', formName).then(res => {
        if (res.status == 1) {
          this.$notify({ title: '成功', message: res.message, type: 'success' })
          this.$router.push({ name: 'login' })
        } else {
          this.$notify({ title: '失败', message: res.message, type: 'error' })
        }
      })
    },
    /*
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('是否确认')
          return false
        }
      })
    },*/
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
.login-box {
  /**/
  border: 1px solid #dcdfe6;
  width: 400px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  /*box-shadow: 0 0 25px #909399;*/
}
.login-title {
  font-size: 20px;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>
