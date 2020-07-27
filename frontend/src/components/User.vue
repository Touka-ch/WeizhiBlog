<template>
  <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
    <el-table-column label="用户名" prop="username"> </el-table-column>
    <el-table-column label="密码" prop="password"> </el-table-column>
    <el-table-column label="昵称" prop="nickname"> </el-table-column>
    <el-table-column label="启用" prop="enable"> </el-table-column>
    <el-table-column label="角色" prop="roles"> </el-table-column>
    <el-table-column label="头像" prop="userFace"> </el-table-column>
    <el-table-column label="邮箱" prop="email"> </el-table-column>
    <el-table-column label="注册时间" prop="regTime"> </el-table-column>
    <el-table-column align="right">
      <template slot="header">
        <!-- slot-scope="scope" -->
        <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
      </template>
      <template slot-scope="scope">
        <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: 'User',
  data() {
    return {
      tableData: [
        {
          username: 'username', //string
          password: 'password', //string
          nickname: 'nickname', //string
          enable: 'true', //boolean
          roles: '普通用户', //List<Role>
          userFace: 'url', //string
          email: 'url', //string
          regTime: '2019-05-10' //timeStamp(long)
        },
        {
          username: 'username', //string
          password: 'password', //string
          nickname: 'nickname', //string
          enable: 'true', //boolean
          roles: '普通用户', //List<Role>
          userFace: 'url', //string
          email: 'url', //string
          regTime: '2019-05-10' //timeStamp(long)
        }
      ],
      search: ''
    }
  },
  mounted() {
    this.$http.get('/user/all').then(res => {
      console.log(res)
    })
  },
  methods: {
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
