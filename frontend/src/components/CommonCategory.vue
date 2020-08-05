<template>
  <el-table :data="tableData">
    <el-table-column prop="address" label="目录"></el-table-column>
  </el-table>
</template>

<script>
export default {
  name: 'commonCategory',
  data() {
    return {
      tableData: [
        {
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          address: '上海市普陀区金沙江路 1518 弄'
        },
        {
          address: '上海市普陀区金沙江路 1518 弄'
        }
      ]
    }
  }
}
</script>

<style scoped>
.el-table {
  margin: 10px 0 30px 0;
  width: 100%;
  border-radius: 5px;
  height: 500px;
}
</style>
