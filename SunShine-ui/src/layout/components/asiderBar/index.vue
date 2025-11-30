<template>

    <el-menu class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse"
        :unique-opened="true" background-color="#fff" text-color="#000000" active-text-color="#1890ff">
        <div class="title">Sunshine 项目管理平台</div>
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

            <el-menu-item @click="goto('/help')" index="3-2">帮助文档</el-menu-item>
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
    overflow: hidden;
    width: 150px;
}

.title {
    display: flex;
    height: 60px;
    justify-content: center;
    margin: 10px auto;
    align-items: center;
    font-size: 13px;
    // font-weight: 200;
    color: #fff;
    background-color: #33589e;
    margin: 0;
    padding: 0;
}
</style>