<template>
    <video ref="video" class="video-js vjs-default-skin" height="400" controls>
        <source :src="src" type="video/mp4" />
    </video>
</template>

<script>
export default {
    data() {
        return {
            player: null, // 用来存储当前 video
        };
    },
    props: ["src"],
    mounted() { // 渲染视频
        this.player = this.$video(this.$refs.video);
    },
}
</script>

<style></style>
