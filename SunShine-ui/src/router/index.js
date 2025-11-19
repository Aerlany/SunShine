import Vue from "vue";
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

import Router from "vue-router";
Vue.use(Router);

import { getToken } from '@/utils/auth'

//使用layout布局
import Layout from '@/layout'

//路由配置
export const Routes = [
    {
        path: '/login',
        component: () => import('@/views/login'),
        hidden: true
    },
    {
        path: '/404',
        component: () => import('@/views/error/404'),
        hidden: true
    },
    {
        path: '/401',
        component: () => import('@/views/error/401'),
        hidden: true
    },
    {
        path: '*',
        redirect: '/404'
    },
    {
        path: '',
        component: Layout,
        children: [
            {
                path: '',
                component: () => import('@/views/welcome'),
                name: 'welcome',
            },
        ]
    }
]

// 防止连续点击多次路由报错
let routerPush = Router.prototype.push;
let routerReplace = Router.prototype.replace;
// push
Router.prototype.push = function push(location) {
    return routerPush.call(this, location).catch(err => err)
}
// replace
Router.prototype.replace = function push(location) {
    return routerReplace.call(this, location).catch(err => err)
}

const router = new Router({
    mode: 'history', // 去掉url中的#
    scrollBehavior: () => ({ y: 0 }),
    routes: Routes
})
export default router;

//路由守卫
NProgress.configure({ showSpinner: false })
const whiteList = ['/login', '/register'];
router.beforeEach((to, from, next) => {
    NProgress.start()
    if (getToken()) {
        // to.meta.title && store.dispatch('settings/setTitle', to.meta.title)
        /* has token*/
        if (to.path === '/login') {
            next();
            // next({ path: '/' })
            NProgress.done()
        } else if (whiteList.indexOf(to.path) !== -1) {
            next()
        } else {
            //TODO 进行权限判断
            next()
        }
    } else {
        // 没有token
        if (whiteList.indexOf(to.path) !== -1) {
            // 在免登录白名单，直接进入
            next()
        } else {
            next(`/login`) // 否则全部重定向到登录页
            NProgress.done()
        }
    }
})

router.afterEach(() => {
    NProgress.done()
})

