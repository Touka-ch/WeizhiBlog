<template>
  <div>
    <el-alert v-if="errors" title="网页走丢了，刷新试试" type="error" effect="dark" center> </el-alert>
    <el-table :data="searchKeyword" style="width: 100%">
      <!--:data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"-->
      <el-table-column label="用户名" prop="username" sortable> </el-table-column>
      <el-table-column label="密码" prop="password"> </el-table-column>
      <el-table-column label="昵称" prop="nickname" sortable> </el-table-column>
      <el-table-column
        prop="enabled"
        label="启用"
        width="100"
        :filters="[
          { text: 'true', value: true },
          { text: 'false', value: false }
        ]"
        :filter-method="filterTag"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === true ? 'success' : 'info'">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="角色" prop="roles">
        <template slot-scope="scope">
          <div v-for="item in scope.row.roles" :key="item">
            <el-tag :type="item === 'ROLE_1' ? 'danger' : 'info'">{{ item }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="头像">
        <template slot-scope="scope">
          <img :src="scope.row.userface" min-width="70" height="70" />
        </template>
      </el-table-column>
      <el-table-column label="邮箱" prop="email"> </el-table-column>
      <el-table-column label="注册时间" prop="regTime" sortable> </el-table-column>
      <el-table-column label="更改头像">
        <template v-slot="scope">
          <el-button size="mini" type="success" @click="handleImgEdit(scope.$index, scope.row)">更改图片</el-button>
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <!-- slot-scope="scope" -->
          <input v-model="search" placeholder="输入关键字搜索" />
        </template>
        <template v-slot="scope">
          <!-- slot-scope="scope" v-slot="scope"-->
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            :disabled="isOrigin || isMine((scope.index, scope.row).id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- Form  小bug 如果原来用户名或者密码大于20个，不点击输入框的时候，不会报错-->
    <el-dialog title="编辑用户" :visible.sync="dialogFormVisible" width="500px">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login-box">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="ruleForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="是否启用">
          <el-switch
            style="display: block"
            v-model="ruleForm.status"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="启用"
            inactive-text="禁用"
            :disabled="isOrigin"
          >
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="编辑图片" :visible.sync="dialogForm2Visible" width="500px">
      <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/" :show-file-list="false" :http-request="uploadFile">
        <img v-if="ruleForm.userface" :src="ruleForm.userface" class="avatar" />
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script>
import { fileRequest, userRequest } from '../api/Requests'
export default {
  name: 'User',
  computed: {
    searchKeyword() {
      if (this.tableData == null) return null
      return this.tableData.filter(data => !this.search || data.username.toLowerCase().includes(this.search.toLowerCase()))
    }
  },
  data() {
    return {
      dialogForm2Visible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      form: {
        name: '',
        region: ''
      },
      ruleForm: {
        id: '',
        username: '',
        password: '',
        nickname: '',
        email: '',
        status: true,
        userface: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }]
      },
      tableData: [
        {
          enable: true
        }
      ],
      search: '',
      info: '',
      errors: '',
      index: null,
      row: '',
      isOrigin: true,
      id: '',
      userFace: ''
    }
  },
  watch: {
    userFace(val, oldval) {
      if (val != oldval) {
        let user = { userface: '', id: this.id }
        user.userface = this.userFace
        userRequest('patch', this.id, user).then(res => {
          console.log(res)
          if (res.status == '1') {
            this.$notify({ type: 'success', message: res.message })
          } else {
            this.$notify.error({ message: res.message })
            console.log()
          }
        })
      }
    }
  },
  created() {
    this.judgeRole()
    this.getAllUser()
  },
  activated() {},
  mounted() {},
  methods: {
    getAllUser() {
      //获取所有用户
      userRequest('get').then(res => {
        this.info = res
        this.tableData = res.object
        if (this.isOrigin) {
          for (let user of this.tableData)
            if (user.id == JSON.parse(localStorage.getItem('user')).id) {
              this.tableData = [user]
              break
            }
        }
        console.log(this.tableData)
        for (let item of this.tableData) {
          //item.enabled = true
          item.password = '**********'
          let data = new Date(item.regTime)
          item.regTime = data.toLocaleString()
        }
        this.error()
      })
    },
    error() {
      if (this.info.status == '0') this.errors = true
      else {
        this.$message({
          message: '数据加载成功',
          type: 'success'
        })
      }
    },
    filterTag(value, row) {
      return row.enabled === value
    },
    judgeRole() {
      for (let item of JSON.parse(localStorage.getItem('user')).roles) {
        if (item == 'ROLE_1') this.isOrigin = false
      }
    },
    /*
    change() {
      this.$forceUpdate()
      console.log('jshajdhash')
    },
    refresh() {
      this.reload()
    },*/
    updateUser() {
      delete this.ruleForm.regTime
      userRequest('patch', this.ruleForm.id, this.ruleForm).then(res => {
        if (res.status == '1') {
          this.$notify({ title: '成功', message: res.message, type: 'success' })
          //this.getAllUser()
        } else this.$notify({ title: '失败', message: res.message, type: 'error' })
      })
      this.dialogFormVisible = false
    },
    handleEdit(index, row) {
      this.row = row
      this.dialogFormVisible = true
      this.ruleForm = (index, row)
      //console.log((index, row).email)
    },
    handleDelete(index, row) {
      //删除用户
      userRequest('delete', (index, row).id).then(res => {
        if (res.status == '1') {
          this.$notify({ title: '成功', message: res.message, type: 'success' })
          //this.getAllUser()
        } else this.$notify.error({ title: '失败', message: res.message })
      })
    },
    handleImgEdit(index, row) {
      this.row = row
      this.dialogForm2Visible = true
      this.ruleForm = (index, row)
    },
    isMine(id) {
      if (id === JSON.parse(localStorage.getItem('user')).id) {
        return true
      } else {
        return false
      }
    },
    uploadFile(content) {
      this.id = (this.index, this.row).id
      let formdata = new FormData()
      formdata.append('file', content.file)
      fileRequest(formdata).then(res => {
        if (res.status == '1') {
          this.$notify({ type: 'success', message: res.message })
          this.userFace = res.object
        } else {
          this.$notify.error({ message: res.message })
        }
      })
    }
  }
}
</script>

<style scoped>
.el-switch {
  margin-top: 9px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
