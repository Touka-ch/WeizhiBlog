module.exports = {
  devServer: {
    port: 8080,
    open: true,
    proxy: {
      // 开发环境代理配置
      '/api': {
        // 匹配 /dev-api 开头的请求，
        // 目标服务器地址，代理访问http://localhost:8001
        target: 'http://47.115.41.198:8090',
        changeOrigin: true, // 开启代理服务器
        pathRewrite: {
          //  会将 /dev-api 替换为 '',也就是 /dev-api 会移除，
          // 如 /dev-api/db.json 代理到 https://localhost:8080/db.json
          '^/api': ''
        }
      }
    }
  },
  css: {
    loaderOptions: {
      sass: {
        prependData: '@import "@/assets/scss/_variable.scss";'
      }
    }
  }
}
