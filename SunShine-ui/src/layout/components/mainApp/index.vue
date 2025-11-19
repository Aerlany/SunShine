<template>
    <section class="app-main">
        <!-- 此处可以添加动画 -->
        <!-- <transition appear name="animate__animated  animate__faster" enter-active-class="animate__fadeInUp"
            leave-active-class="animate__fadeOutRight"> -->
        <router-view />
        <!-- </transition> -->
    </section>
</template>

<script>
import animate from "animate.css";
export default {
    name: "mainApp",
    computed: {
    }
}
</script>
<style lang="scss" scoped></style>