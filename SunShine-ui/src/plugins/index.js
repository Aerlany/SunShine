
import cache from './cache'
import modal from './modal'
import { mixin } from "./mixin";


export default {
    install(Vue) {
        // 缓存对象
        Vue.prototype.$cache = cache
        // 模态框对象
        Vue.prototype.$modal = modal

        //全局方法混入
        Vue.mixin(mixin);
    }
}