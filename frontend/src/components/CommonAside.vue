<template>
  <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
    <el-submenu index="1" v-for="(item, index) in hasChildren" :key="index">
      <template slot="title">
        <i :class="'el-icon-' + item.icon"></i>
        <span>{{ item.label }}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item :index="item.path" v-for="(subItem, subIndex) in item.children" :key="subIndex">
          <i :class="'el-icon-' + subItem.icon"></i>
          <span>{{ subItem.label }}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-menu-item :index="item.path" v-for="item in noChildren" :key="item.path">
      <i :class="'el-icon-' + item.icon"></i>
      <span slot="title">{{ item.label }}</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'CommonAside.',
  computed: {
    noChildren() {
      return this.asideMenu.filter(item => !item.children)
    },
    hasChildren() {
      return this.asideMenu.filter(item => item.children)
    }
  },
  data() {
    return {
      asideMenu: [
        {
          path: '/article',
          label: '文章管理',
          icon: 'notebook-1',
          children: [
            {
              path: '/page1',
              label: '文章列表',
              icon: 'folder'
            },
            {
              path: '/page2',
              label: '发表文章',
              icon: 'document-add'
            }
          ]
        },
        {
          path: '/user',
          label: '用户管理',
          icon: 'user'
        },
        {
          path: '/column',
          label: '栏目管理',
          icon: 's-operation'
        },
        {
          path: '/',
          label: '数据统计',
          icon: 's-data'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.el-menu {
  height: 100%;
  border: none;
}
</style>
