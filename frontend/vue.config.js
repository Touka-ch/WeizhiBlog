module.exports = {
  devServer: {
    port: 8080,
    open: true
  },
  css: {
    loaderOptions: {
      sass: {
        prependData: '@import "@/assets/scss/_variable.scss";'
      }
    }
  }
}
