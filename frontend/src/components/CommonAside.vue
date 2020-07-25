<template>
  <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
    <el-submenu i="1" v-for="(item, i) in hasChildren" :key="i">
      <template slot="title">
        <i :class="'el-icon-' + item.icon"></i>
        <span>{{ item.label }}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item :i="item.path" v-for="(subItem, subi) in item.children" :key="subi">
          <i :class="'el-icon-' + subItem.icon"></i>
          <span>{{ subItem.label }}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-menu-item :i="item.path" v-for="item in noChildren" :key="item.path">
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
      return this.asideMenu.filter((item => !item.children) && (item => item.seen))
    },
    hasChildren() {
      return this.asideMenu.filter(item => item.children)
    }
  },
  data() {
    return {
      asideMenu: [
        {
          path: '/hub',
          label: '公共区',
          icon: 'loading',
          seen: true
        },
        {
          path: '/article',
          label: '文章管理',
          icon: 'notebook-1',
          seen: true,
          children: [
            {
              path: '/page1',
              label: '文章列表',
              icon: 'folder',
              seen: true
            },
            {
              path: '/page2',
              label: '发表文章',
              icon: 'document-add',
              seen: true
            }
          ]
        },
        {
          path: '/user',
          label: '用户管理',
          icon: 'user',
          seen: true
        },
        {
          path: '/column',
          label: '目录管理',
          icon: 's-operation',
          seen: true
        },
        {
          path: '/',
          label: '数据统计',
          icon: 's-data',
          seen: true
        }
      ]
    }
  },
  mounted: function() {
    for (let i = 0; i < this.noChildren.length; i++) {
      const element = this.noChildren[i]
      if (element.label == '用户管理') {
        element.seen = false
      }
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
