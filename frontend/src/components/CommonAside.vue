<template>
  <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" :collapse="isCollapse">
    <el-button type="primary" icon="el-icon-menu" @click="collapse"></el-button>
    <el-submenu :index="item.path" v-for="item in hasChildren" :key="item.path">
      <!--"(item, index) in hasChildren" :key="index"-->
      <template slot="title">
        <i :class="'el-icon-' + item.icon"></i>
        <span>{{ item.label }}</span>
      </template>
      <el-menu-item :index="subItem.path" v-for="subItem in item.children" :key="subItem.path" @click="clickMenu(subItem)">
        <i :class="'el-icon-' + subItem.icon"></i>
        <span>{{ subItem.label }}</span>
      </el-menu-item>
    </el-submenu>
    <el-menu-item :index="item.path" v-for="item in noChildren" :key="item.path" @click="clickMenu(item)">
      <i :class="'el-icon-' + item.icon"></i>
      <span>{{ item.label }}</span>
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
          path: '/hub',
          name: 'hub',
          label: '公共区',
          icon: 'loading',
          seen: true
        },
        {
          path: '/article',
          name: 'article',
          label: '文章管理',
          icon: 'notebook-1',
          seen: true,
          children: [
            {
              path: '/list',
              name: 'list',
              label: '文章列表',
              icon: 'folder',
              seen: true
            },
            {
              path: '/new',
              name: 'new',
              label: '发表文章',
              icon: 'document-add',
              seen: true
            }
          ]
        },
        {
          path: '/user',
          name: 'user',
          label: '用户管理',
          icon: 'user',
          seen: true
        },
        {
          path: '/column',
          name: 'column',
          label: '栏目管理',
          icon: 's-operation',
          seen: true
        },
        {
          path: '/chart',
          name: 'chart',
          label: '数据统计',
          icon: 's-data',
          seen: true
        }
      ],
      isCollapse: false,
      width: ''
    }
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse
      this.$emit('collapse', this.isCollapse)
      //console.log('传送isCollapse')
      return this.isCollapse
    },
    clickMenu(item) {
      this.$router.push({ name: item.name })
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
