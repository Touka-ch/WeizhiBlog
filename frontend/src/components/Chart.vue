<template>
  <div>
    <el-card class="title">博客数据统计</el-card>
    <br />
    <el-row :gutter="20">
      <el-col :span="5.5">
        <el-card shadow="never" class="box">
          <div class="boxNum">8</div>
          文章总数
        </el-card>
      </el-col>
      <el-col :span="5.5">
        <el-card shadow="never" class="box">
          <div class="boxNum">8</div>
          文章总数
        </el-card>
      </el-col>
    </el-row>
    <e-chart style="width:600px; height:300px" :chart-data="this.echartData.pv"></e-chart>
  </div>
</template>

<script>
import EChart from './CommonEChart'
export default {
  name: 'Chart',
  components: { EChart },
  data() {
    return {
      echartData: {
        pv: {
          xData: {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
          },

          series: [
            {
              name: '搜索引擎',
              type: 'line',
              stack: '总量',
              label: {
                normal: {
                  show: true,
                  position: 'top'
                }
              },
              lineStyle: {
                color: '#F95B6C'
              },
              areaStyle: {
                normal: {
                  color: '#FFE1E5',
                  globalCoord: false // 缺省为 false
                }
              },
              data: [820, 932, 901, 934, 1290, 1330, 1320],
              smooth: true
            }
          ]
        },
        comment: {
          xData: [],
          series: []
        }
      }
    }
  }
}
</script>

<style scoped>
.title {
  width: 200px;
  height: 40px;
  background-color: #ca0c16;
  color: white;
  text-align: center;
  line-height: 5px;
}
.boxNum {
  font-size: 25px;
  font-weight: bold;
  font-family: Arial;
  line-height: 20px;
  color: #000000;
}
.box {
  width: 220px;
  height: 90px;
  font-size: 15px;
  background-color: #f2f5f7;
  line-height: 55px;
  color: #999999;
}
</style>
