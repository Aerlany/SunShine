<template>
    <el-menu class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse"
        :unique-opened="true" background-color="#545c64" text-color="#fff" active-text-color="aqua">
        <h2 class="title">{{ !isCollapse ? "工程项目管理平台" : "" }}</h2>
        <el-menu-item @click="goto('/')" index="1">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
        </el-menu-item>
        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-s-data"></i>
                <span slot="title">系统管理</span>
            </template>
            <el-menu-item @click="goto('/project')" index="2-1">项目管理</el-menu-item>
        </el-submenu>

        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-s-check"></i>
                <span slot="title">用户管理</span>
            </template>
            <el-menu-item @click="goto('/user')" index="3-1">用户管理</el-menu-item>

        </el-submenu>

    </el-menu>
</template>

<script>
export default {
    data() {
        return {

        };
    },
    computed: {
        isCollapse() {
            return this.$store.state.navBar.isCollapse
        }
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
    },
}
</script>

<style lang="scss" scoped>
.el-menu {
    border: none;
    height: 100%;
    // overflow: hidden;
}

.title {
    text-align: center;
    margin: 10px auto;
    font-size: 13px;
    font-weight: 200;
    color: #fff;
}
</style>