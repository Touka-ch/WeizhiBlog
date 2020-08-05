<template>
  <div>
    <el-row class="art-item" v-for="item in listData" :key="item.id">
      <el-card shadow="hover">
        <el-row class="art-item" style="font-size: 20px">
          <div class="art-title">
            <span></span>
          </div>
        </el-row>
        <el-row style="border: 5px">
          <el-col :span="5">
            <i class="el-icon-time">
              <span>{{ item.publishDate }}</span>
            </i>
          </el-col>
          <el-col :span="4">
            <i class="el-icon-collection-tag">
              <span>：</span>
              <el-tag size="mini" style="font-size: 14px"><!--{{ this.listData. }}--></el-tag>
              <!--文章没标签-->
            </i>
          </el-col>
        </el-row>
        <el-row class="art-body">
          <div class="side-img hidden-sm-and-down">
            <img class="art-banner" src="../assets/images/1.jpg" />
          </div>
          <div class="side-abstract">
            <div class="art-abstract">{{ item.summary }}</div>
            <div class="art-more">
              <el-button @click="postId(item.id)">阅读全文</el-button>
              <div class="view">
                <i class="el-icon-view"></i>
                <span>{{ item.pageView }}</span>
              </div>
            </div>
          </div>
        </el-row>
      </el-card>
      <img class="star" src="../assets/images/star.png" />
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'CommonList',
  props: {
    commonListData: {
      type: Object,
      default() {
        return {
          object: [
            {
              id: '',
              title: '',
              mdContent: '',
              htmlContent: '',
              summary: '',
              cid: '',
              uid: '',
              publishDate: '',
              editTime: '',
              commentNum: '',
              likeNum: '',
              State: '',
              pageView: '',
              publicToOthers: ''
            }
          ]
        }
      }
    }
  },
  data() {
    return {
      listData: [
        {
          id: '',
          title: '',
          mdContent: '',
          htmlContent: '',
          summary: '',
          cid: '',
          uid: '',
          publishDate: '',
          editTime: '',
          commentNum: '',
          likeNum: '',
          State: '',
          pageView: '',
          publicToOthers: ''
        }
      ]
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      this.listData = this.commonListData.object
    },
    postId(id) {
      this.$emit('postId', id)
    }
  }
}
</script>

<style scoped>
#side .item {
  margin-bottom: 30px;
}

.art-item {
  margin: 10px 0 30px 0;
  position: relative;
}

.art-item .star {
  width: 60px;
  height: 60px;
  position: absolute;
  top: 0;
  right: 0;
}

img.tag {
  width: 16px;
  height: 16px;
}

.art-title {
  border-left: 3px solid #f56c6c;
  padding-left: 5px;
  cursor: pointer;
}

.art-title:hover {
  padding-left: 10px;
  color: #409eff;
}

.art-body {
  display: flex;
  padding: 10px 0;
}

.side-img {
  height: 150px;
  width: 270px;
  overflow: hidden;
  margin-right: 10px;
}

img.art-banner {
  width: 100%;
  height: 100%;
  transition: all 0.6s;
}

img.art-banner:hover {
  transform: scale(1.4);
}

.side-abstract {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.art-abstract {
  flex: 1;
  color: #aaa;
}

.art-more {
  height: 40px;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.art-more .view {
  color: #aaa;
}
h5 {
  font-size: 18px;
}
</style>
