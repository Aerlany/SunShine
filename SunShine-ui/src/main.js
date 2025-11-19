import Vue from 'vue'
import App from './App.vue'
//全局样式引入
import '@/assets/styles/index.scss' // global css

//vue-router
import router from './router'
//vuex
import store from './store'

//组件库
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

//请求
import { api } from "./utils/request";
Vue.prototype.api = api;

//时间格式化
import moment from 'moment';
moment.locale("zh-CN");
Vue.prototype.$moment = moment;

//插件
import plugins from "@/plugins";
Vue.use(plugins);

Vue.config.productionTip = false
new Vue({
  beforeCreate() {
    Vue.prototype.$bus = this; //全局事件总线
  },
  router,
  store,
  render: h => h(App),
}).$mount('#app')