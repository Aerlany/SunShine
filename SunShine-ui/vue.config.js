const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false, //关闭语法检查
  devServer: {
    port: 7464,
    host: "localhost",

    //解决跨域问题
    proxy: {
      //统一图片请求接口
      // '/img': {
      //   target: "http://localhost:8082",
      //   // 允许跨域
      //   changeOrigin: true,
      //   pathRewrite: {
      //     '^/img': ''
      //   }
      // }
    }
  },
})
