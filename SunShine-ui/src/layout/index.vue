<template>
   <div class="app-wrapper">
      <el-container>
         <el-aside width="auto" class="asiderBar">
            <AsiderBar />
         </el-aside>
         <el-container>
            <el-header class="header">
               <TopBar />
            </el-header>
            <el-main class="mainApp">
               <MainApp />
            </el-main>
         </el-container>
      </el-container>
   </div>
</template>

<script>
import { AsiderBar, MainApp, TopBar } from "./components";
export default {
   name: "Layout",
   components: {
      AsiderBar,
      MainApp,
      TopBar,
   },
};
</script>

<style lang="scss" scoped>
.header {
   position: sticky;
   top: 0;
   z-index: 2;
   background-color: #fff;
   -webkit-box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
   box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
}

.asiderBar {
   position: sticky;
   top: 0;
   background-color: #ebf0f4;
   height: 100vh;
   -webkit-box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
   box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
}
</style>
