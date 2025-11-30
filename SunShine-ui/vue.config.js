const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false, //关闭语法检查
  devServer: {
    port: 7464,
    host: "localhost",
    //解决跨域问题
    proxy: {
      '/api': {
        target: 'http://localhost:8082',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/api'
        }
      },
      // 代理Swagger相关请求
      '/swagger-ui': {
        target: 'http://localhost:8082',
        changeOrigin: true
      },
      '/webjars': {
        target: 'http://localhost:8082',
        changeOrigin: true
      },
      '/v2/api-docs': {
        target: 'http://localhost:8082',
        changeOrigin: true
      },
      '/swagger-resources': {
        target: 'http://localhost:8082',
        changeOrigin: true
      }
    },
  },
})
