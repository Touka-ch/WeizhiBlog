<template>
  <div style="height:100%">echart</div>
  <!--ref="echart"-->
</template>
<script>
import echarts from 'echarts'
export default {
  name: 'EChart',
  props: {
    chartData: {
      type: Object,
      default() {
        return {
          xData: [],
          series: []
        }
      }
    },
    isAxisChart: {
      type: Boolean,
      default: true
    }
  },
  computed: {
    options() {
      return this.isAxisChart ? this.axisOption : this.normalOption
    }
  },
  /*watch: {
    chartData: {
      handler: function() {
        this.initChart()
      },
      deep: true
    }
  }, */
  data() {
    return {
      echart: null,
      axisOption: {
        //滑动显示坐标
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        grid: {
          //控制echarts位置
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: []
      },
      normalOption: {
        series: []
      }
    }
  },
  methods: {
    initChart() {
      //console.log('initChart')
      this.initChartData()
      if (this.echart) {
        this.echart.setOption(this.options())
      } else {
        //console.log(this.$ref.echarts)
        this.echart = echarts.init(this.$el)
        //this.echart = echarts.init(this.$ref.echarts)
        //console.log(document.getElementById('main'))
        //this.echart = echarts.init(document.getElementById('main'))
        console.log(this.options)
        console.log(this.axisOption)
        this.echart.setOption(this.options)
      }
    },
    initChartData() {
      //console.log('initChartData')
      if (this.isAxisChart) {
        //可以在这里设置返回axis整个数据 或是只有x轴data数据
        //this.axisOption.xAxis.data = this.chartData.xData.data
        this.axisOption.xAxis = this.chartData.xData
        this.axisOption.series = this.chartData.series
      } else {
        this.normalOption.series = this.chartData.series
      }
    },
    resizeChart() {
      this.echart ? this.echart.resize() : ''
    }
  },
  mounted() {
    this.initChart()
    window.addEventListener('resize', this.resizeChart)
  },
  destroyed() {
    window.removeEventListener('resize', this.resizeChart)
  }
}
</script>

<style scoped></style>
