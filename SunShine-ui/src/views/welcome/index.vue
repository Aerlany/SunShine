<template>
   <div class="welcome">
      <el-row>
         <el-col :span="6">
            <div class="grid-content bg-purple">
               <el-card shadow="hover">
                  <p class="card_title">用户信息</p>
                  <div class="user">
                     <img src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" alt="" />
                     <div>
                        <p>Admin</p>
                        <p class="access">超级管理员</p>
                     </div>
                  </div>
                  <div class="detail">
                     <el-divider></el-divider>
                     <el-descriptions class="margin-top" :column="2">
                        <el-descriptions-item label="用户名">admin</el-descriptions-item>
                        <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
                        <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
                        <el-descriptions-item label="备注">
                           <el-tag size="small">学校</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188
                           号</el-descriptions-item>
                     </el-descriptions>
                  </div>
               </el-card>
               <el-card class="log" shadow="hover">
                  <p class="card_title">项目日志</p>
                  <div>
                     <el-timeline>
                        <el-timeline-item timestamp="2018/4/12" placement="top">
                           <el-card>
                              <h4>更新 Github 模板</h4>
                              <p>王小虎 提交于 2018/4/12 20:46</p>
                           </el-card>
                        </el-timeline-item>
                        <el-timeline-item timestamp="2018/4/3" placement="top">
                           <el-card>
                              <h4>更新 Github 模板</h4>
                              <p>王小虎 提交于 2018/4/3 20:46</p>
                           </el-card>
                        </el-timeline-item>
                        <el-timeline-item timestamp="2018/4/2" placement="top">
                           <el-card>
                              <h4>更新 Github 模板</h4>
                              <p>王小虎 提交于 2018/4/2 20:46</p>
                           </el-card>
                        </el-timeline-item>
                        <el-timeline-item timestamp="2018/4/2" placement="top">
                           <el-card>
                              <h4>更新 Github 模板</h4>
                              <p>王小虎 提交于 2018/4/2 20:46</p>
                           </el-card>
                        </el-timeline-item>
                        <el-timeline-item timestamp="2018/4/2" placement="top">
                           <el-card>
                              <h4>更新 Github 模板</h4>
                              <p>王小虎 提交于 2018/4/2 20:46</p>
                           </el-card>
                        </el-timeline-item>
                        <el-timeline-item timestamp="2018/4/2" placement="top">
                           <el-card>
                              <h4>更新 Github 模板</h4>
                              <p>王小虎 提交于 2018/4/2 20:46</p>
                           </el-card>
                        </el-timeline-item>
                     </el-timeline>
                  </div>
               </el-card>
            </div>
         </el-col>
         <el-col :span="18">
            <div class="grid-content bg-purple-light">
               <el-card shadow="hover">
                  <p class="card_title">项目概况</p>
                  <div>
                     <el-table :data="tableData" border style="width: 100%">
                        <el-table-column prop="date" label="日期" width="180">
                        </el-table-column>
                        <el-table-column prop="name" label="项目名" width="180">
                        </el-table-column>
                        <el-table-column prop="address" label="地址">
                        </el-table-column>
                     </el-table>
                  </div>
               </el-card>
               <el-card shadow="hover">
                  <p class="card_title" style="margin-top: 15px">项目统计</p>
                  <div ref="echars1" class="echars1"></div>
               </el-card>
               <el-row>
                  <el-col :span="12">
                     <el-card shadow="hover">
                        <p class="card_title" style="margin-top: 15px">
                           项目统计
                        </p>
                        <div ref="echars2" class="echars1"></div>
                     </el-card>
                  </el-col>
                  <el-col :span="12">
                     <el-card shadow="hover">
                        <p class="card_title" style="margin-top: 15px">
                           项目统计
                        </p>
                        <div ref="echars3" class="echars1"></div>
                     </el-card>
                  </el-col>
               </el-row>
            </div>
         </el-col>
      </el-row>
   </div>
</template>

<script>
import * as echarts from "echarts";
import "echarts/extension/bmap/bmap";
export default {
   data() {
      return {
         tableData: [
            {
               date: "2016-05-02",
               name: "王小虎",
               address: "上海市普陀区金沙江路 1518 弄",
            },
            {
               date: "2016-05-04",
               name: "王小虎",
               address: "上海市普陀区金沙江路 1517 弄",
            },
            {
               date: "2016-05-01",
               name: "王小虎",
               address: "上海市普陀区金沙江路 1519 弄",
            },
            {
               date: "2016-05-03",
               name: "王小虎",
               address: "上海市普陀区金沙江路 1516 弄",
            },
         ],
         option1: {
            title: {
               text: "Stacked Line",
            },
            tooltip: {
               trigger: "axis",
            },
            legend: {
               data: [
                  "Email",
                  "Union Ads",
                  "Video Ads",
                  "Direct",
                  "Search Engine",
               ],
            },
            grid: {
               left: "3%",
               right: "4%",
               bottom: "3%",
               containLabel: true,
            },
            toolbox: {
               feature: {
                  saveAsImage: {},
               },
            },
            xAxis: {
               type: "category",
               boundaryGap: false,
               data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
            },
            yAxis: {
               type: "value",
            },
            series: [
               {
                  name: "Email",
                  type: "line",
                  stack: "Total",
                  data: [120, 132, 101, 134, 90, 230, 210],
               },
               {
                  name: "Union Ads",
                  type: "line",
                  stack: "Total",
                  data: [220, 182, 191, 234, 290, 330, 310],
               },
               {
                  name: "Video Ads",
                  type: "line",
                  stack: "Total",
                  data: [150, 232, 201, 154, 190, 330, 410],
               },
               {
                  name: "Direct",
                  type: "line",
                  stack: "Total",
                  data: [320, 332, 301, 334, 390, 330, 320],
               },
               {
                  name: "Search Engine",
                  type: "line",
                  stack: "Total",
                  data: [820, 932, 901, 934, 1290, 1330, 1320],
               },
            ],
         },
         option2: {
            xAxis: {
               type: "category",
               data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
            },
            yAxis: {
               type: "value",
            },
            series: [
               {
                  data: [120, 200, 150, 80, 70, 110, 130],
                  type: "bar",
                  showBackground: true,
                  backgroundStyle: {
                     color: "rgba(180, 180, 180, 0.2)",
                  },
               },
            ],
         },
         option3: {
            title: {
               text: "Referer of a Website",
               subtext: "Fake Data",
               left: "center",
            },
            tooltip: {
               trigger: "item",
            },
            legend: {
               orient: "vertical",
               left: "left",
            },
            series: [
               {
                  name: "Access From",
                  type: "pie",
                  radius: "50%",
                  data: [
                     { value: 1048, name: "Search Engine" },
                     { value: 735, name: "Direct" },
                     { value: 580, name: "Email" },
                     { value: 484, name: "Union Ads" },
                     { value: 300, name: "Video Ads" },
                  ],
                  emphasis: {
                     itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: "rgba(0, 0, 0, 0.5)",
                     },
                  },
               },
            ],
         },
      };
   },
   methods: {
      initeCharts(dom, option) {
         const chartDom = dom;
         var myChart = echarts.init(chartDom);
         myChart.setOption(option);
      },
   },
   mounted() {
      this.initeCharts(this.$refs.echars1, this.option1);
      this.initeCharts(this.$refs.echars2, this.option2);
      this.initeCharts(this.$refs.echars3, this.option3);
      this.mapCharts();

      this.$store.commit("RESET_topBarDisPlayTags");
      this.$store.commit("PUSH_RESET_topBarDisPlayTags", "首页");
   },
};
</script>

<style lang="scss" scoped>
.user {
   display: flex;
   align-items: center;

   img {
      width: 50%;
      height: 50%;
      border-radius: 50%;
      margin-right: 10%;
   }
}

.access {
   font-size: 10px;
   color: gray;
}

.echars1 {
   height: 500px;
}

.echars2 {
   height: 500px;
}

.log {
   height: auto;
   overflow-y: auto;
}
</style>
