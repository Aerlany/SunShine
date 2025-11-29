<template>
    <div>
        <el-cascader size="large" :options="options" v-model="selectedOptions" @change="handleChange">
        </el-cascader>
    </div>
</template>

<script>
import { pcaTextArr } from 'element-china-area-data'

export default {
    data() {
        return {
            options: pcaTextArr,
            selectedOptions: []
        }
    },
    props: ["loactionInfo"],
    mounted() {
        console.log(pcaTextArr);
    },

    methods: {
        handleChange() {
            var string = "";
            for (let i = 0; i < this.selectedOptions.length; i++) {
                string += this.selectedOptions[i];
            }
            this.$emit("getString", string)
            console.log(string);
        }
    }
}

</script>

<style></style>