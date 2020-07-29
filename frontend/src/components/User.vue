<template>
  <div>
    <el-alert v-if="errors" title="网页走丢了，刷新试试" type="error" effect="dark" center> </el-alert>
    <el-table :data="searchKeyword" style="width: 100%">
      <!--:data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"-->
      <el-table-column label="用户名" prop="username" sortable> </el-table-column>
      <el-table-column label="密码" prop="password"> </el-table-column>
      <el-table-column label="昵称" prop="nickname"> </el-table-column>
      <el-table-column label="启用" prop="enabled"> </el-table-column>
      <el-table-column label="角色" prop="roles"> </el-table-column>
      <el-table-column label="头像">
        <!--prop="userface"-->
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
    <!--{{ search }}-->
    <!--{{ info }}-->
    <!--{{ tableData }}-->
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
    /*
    change() {
      this.$forceUpdate()
      console.log('jshajdhash')
    },*/
    refresh() {
      this.reload()
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
