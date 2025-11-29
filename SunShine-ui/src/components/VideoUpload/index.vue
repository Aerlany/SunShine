<template>
    <div>
        <el-upload class="upload-demo" :action="url" :headers="header" :on-preview="handlePreview"
            :on-remove="handleRemove" :before-remove="beforeRemove" multiple :file-list="fileList" list-type="picture">
            <el-button type="text" icon="el-icon-upload">点击上传视频</el-button>
            <!-- <span style="margin-left: 10px;" slot="tip" class="el-upload__tip">现只能上传jpg/png文件。</span> -->
        </el-upload>
        <el-dialog :show-close="false" :visible.sync="dialogVisible" v-if="dialogVisible" width="60%" append-to-body>
            <VideoPreview class="video" :src="dialogImageUrl"></VideoPreview>
        </el-dialog>

    </div>
</template>

<script>
import { getToken } from '@/utils/auth'
import VideoPreview from "@/components/VideoPreview";

export default {
    components: { VideoPreview },
    data() {
        return {
            fileList: [
            ],
            dialogImageUrl: '',
            dialogVisible: false,
            baseUrl: "http://localhost:8082",
            header: { "Token": getToken() },
            url: "",
        }
    },
    props: ["action", "targetId", "pictureIds"],
    methods: {
        //TODO 文件上传功能
        handleRemove(file, fileList) {
            var deleteUrl = this.action + this.targetId + "/" + file.name + "/deleteFiles";
            this.api.del(deleteUrl, "", () => {

            })
        },
        handlePreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${file.name}？`);
        },
    },
    mounted() {
        this.fileList = [];
        this.url = this.baseUrl + this.action + this.targetId + "/uploadFiles";
        var a = JSON.parse(this.pictureIds);
        a = a.videoIDs;
        for (let index = 0; index < a.length; index++) {
            var path = "/img/staticResource/" + a[index] + "/getFileById";
            this.fileList.push({ name: a[index], url: path })
        }
    },
}
</script>

<style>
.video {
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin-top: 5px;
}
</style>