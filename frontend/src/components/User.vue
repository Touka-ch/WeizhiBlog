<template>
  <div>
    <el-alert v-if="errors" title="网页走丢了，刷新试试" type="error" effect="dark" center> </el-alert>
    <el-table :data="searchKeyword" style="width: 100%">
      <!--:data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"-->
      <el-table-column label="用户名" prop="username" sortable> </el-table-column>
      <el-table-column label="密码" prop="password"> </el-table-column>
      <el-table-column label="昵称" prop="nickname"> </el-table-column>
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
          <el-tag :type="scope.row.enabled == true ? 'success' : 'info'">{{ scope.row.enabled }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="角色" prop="roles"> </el-table-column>
      <el-table-column label="头像">
        <template slot-scope="scope">
          <img :src="scope.row.userface" min-width="70" height="70" />
        </template>
      </el-table-column>
      <el-table-column label="邮箱" prop="email"> </el-table-column>
      <el-table-column label="注册时间" prop="regTime" sortable> </el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <!-- slot-scope="scope" -->
          <input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- Form -->
    <el-dialog title="编辑用户" :visible.sync="dialogFormVisible">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login-box">
        <h2 class="login-title">注册新用户</h2>
        <el-form-item label="用户名" prop="username">
          <el-input placeholder="不少于3个字符" v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input placeholder="想个好听的名字吧!" v-model="ruleForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" placeholder="不少于3个字符" v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input placeholder="请输入常用的邮箱" v-model="ruleForm.email"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
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
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      form: {
        name: '',
        region: ''
      },
      ruleForm: {
        username: '',
        password: '',
        nickname: '',
        email: ''
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
      tableData: [{}],
      search: '',
      info: '',
      errors: ''
    }
  },
  created() {
    this.$http.get('/user/all').then(res => {
      this.info = res.data
      this.tableData = res.data.object
      //this.tableData = res.data.object
      //console.log(res.data)
      this.error()
    })
    //this.error('created')
  },
  mounted() {
    this.error()
  },
  methods: {
    error() {
      if (this.info.status == 0) this.errors = true
    },
    filterTag(value, row) {
      return row.enabled === value
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
      this.$http.post('/user/update').then(res => {
        console.log(res)
      })
      this.dialogFormVisible = false
    },
    handleEdit(index, row) {
      this.dialogFormVisible = true
      this.ruleForm.username = (index, row).username
      this.ruleForm.password = (index, row).password
      this.ruleForm.nickname = (index, row).nickname
      this.ruleForm.email = (index, row).email
      //console.log((index, row).email)
    },
    handleDelete(index, row) {
      this.$http.post('/user/delete').then(res => {
        console.log(res)
      })
      console.log(index, row)
    }
  }
}
</script>

<style scoped></style>
