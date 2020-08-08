<template>
  <div>
    <el-tabs v-model="editableTabsValue" type="card" closable @edit="removeTab">
      <el-tab-pane :key="item.name" v-for="item in editableTabs" :name="item.name" :label="item.label">
        <div v-show="item.name === 'category'">
          <el-table :data="categoriesData">
            <el-table-column prop="cateName" label="目录"></el-table-column>
            <el-table-column prop="date" label="创建时间"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                <el-button size="mini" type="warning" @click="addTab(scope.$index, scope.row)">进入目录</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div>
            <el-input placeholder="请输入内容" v-model="newCategory.cateName">
              <template slot="append"><el-button @click="addCategory">添加新目录</el-button></template>
            </el-input>
          </div>
        </div>
        <div v-show="item.name != 'category'">
          <el-table :data="item.object">
            <el-table-column prop="title" label="文章标题"></el-table-column>
            <el-table-column label="操作"></el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
    <!-- 修改目录-->
    <el-dialog title="修改名称" :visible.sync="dialogFormVisible" width="500px">
      <el-form :model="form">
        <el-form-item label="目录名称">
          <el-input v-model="category.cateName" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateCategory">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { articleUserCategoryRequest, categoryRequest } from '../api/Requests'

export default {
  name: 'commonCategory',
  props: {
    commonCategory: {
      type: Array,
      default() {
        return [
          {
            id: '',
            cateName: '',
            date: '',
            uid: ''
          }
        ]
      }
    }
  },
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      categoriesData: [],
      dialogFormVisible: false,
      category: {},
      newCategory: {},
      editableTabsValue: '1',
      editableTabs: [
        {
          label: '目录页',
          name: 'category'
        }
      ],
      tabIndex: 1,
      tabVisible: false
    }
  },
  watch: {
    commonCategory(val, oldval) {
      if (val != oldval) {
        this.categoriesData = this.commonCategory
        this.transmit(this.categoriesData)
      }
    }
  },
  methods: {
    transmit(data) {
      for (let item of data) {
        let data = new Date(item.date)
        //console.log(data.toLocaleString())
        item.date = data.toLocaleString()
      }
    },
    handleEdit(index, row) {
      this.category = (index, row)
      this.dialogFormVisible = true
    },
    handleDelete(index, row) {
      //console.log(index, row)
      categoryRequest('delete', (index, row).id, (index, row)).then(res => {
        if (res.status == '1') {
          this.$notify({ title: '成功', message: res.message, type: 'success' })
        } else this.$notify.error({ title: '失败', message: res.message })
        window.location.reload()
      })
    },
    updateCategory() {
      this.dialogFormVisible = false
      delete this.category.date
      categoryRequest('patch', this.category.id, this.category).then(res => {
        if (res.status == '1') {
          this.$notify({ title: '成功', message: res.message, type: 'success' })
        } else this.$notify.error({ title: '失败', message: res.message })
      })
    },
    addCategory() {
      this.newCategory.uid = JSON.parse(localStorage.getItem('user')).id
      console.log(JSON.parse(localStorage.getItem('user')).id)
      categoryRequest('post', '', this.newCategory).then(res => {
        console.log(res)
        if (res.status == '1') {
          this.$notify({ title: '成功', message: res.message, type: 'success' })
        } else this.$notify.error({ title: '失败', message: res.message })
        window.location.reload()
      })
    },
    removeTab(targetName) {
      if (targetName === 'category') {
        this.$message.error({ title: '失败', message: '不可以关闭目录' })
      } else {
        let tabs = this.editableTabs
        let activeName = this.editableTabsValue
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1]
              if (nextTab) {
                activeName = nextTab.name
              }
            }
          })
        }
        this.editableTabsValue = activeName
        this.editableTabs = tabs.filter(tab => tab.name !== targetName)
      }
    },
    addTab(index, row) {
      console.log((index, row))
      let newTabName = ++this.tabIndex + ''
      articleUserCategoryRequest((index, row).uid, (index, row).id).then(res => {
        if (res.status == '1') {
          this.$message({ type: 'success', message: res.message })
        } else {
          this.$message.error({ message: res.message })
        }
        this.editableTabs.push({
          label: (index, row).cateName,
          name: (index, row).id,
          object: res.object
        })
        this.editableTabsValue = newTabName
      })
    }
  }
}
</script>

<style scoped>
.el-table {
  margin: 10px 0 30px 0;
  width: 100%;
  border-radius: 5px;
  /*height: 500px;*/
}
</style>
