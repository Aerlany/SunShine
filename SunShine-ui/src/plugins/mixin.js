export const mixin = {
    methods: {
        //统一路由跳转
        goto(path, params) {
            if (params)
                this.$router.push({
                    path: path,
                    query: params
                })
            else
                this.$router.push({ path: path })
        }
    }
}