<template>
  <div>
    <el-table :data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
      <!--:data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"-->
      <el-table-column label="用户名" prop="username" sortable> </el-table-column>
      <el-table-column label="密码" prop="password"> </el-table-column>
      <el-table-column label="昵称" prop="nickname"> </el-table-column>
      <el-table-column label="启用" prop="enabled"> </el-table-column>
      <el-table-column label="角色" prop="roles"> </el-table-column>
      <el-table-column label="头像" prop="userface"> </el-table-column>
      <el-table-column label="邮箱" prop="email"> </el-table-column>
      <el-table-column label="注册时间" prop="regTime"> </el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <!-- slot-scope="scope" -->
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template>
        <template>
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    {{ search }}
    {{ info }}
    {{ tableData }}
  </div>
</template>

<script>
export default {
  name: 'User',
  computed: {},
  data() {
    return {
      tableData: [{}, {}],
      search: '',
      info: ''
    }
  },
  created() {
    this.$http.get('/user/all').then(res => {
      this.info = res.data
      this.tableData = res.data.object
      //this.tableData = res.data.object
      //console.log(res.data)
    })
  },
  mounted() {
    //this.error()
  },
  methods: {
    error() {
      if (this.info.status == 0) {
        this.$message.error('错了哦，这是一条错误消息')
        console.log(this.info.status)
      }
    },
    change() {
      this.$forceUpdate()
      console.log('jshajdhash')
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    }
  }
}
</script>

<style scoped></style>
