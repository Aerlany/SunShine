<template>
   <div class="app-wrapper">
      <el-container>
         <transition name="slide-left">
            <el-aside v-if="!isCollapse" width="auto" class="asiderBar">
               <AsiderBar />
            </el-aside>
         </transition>
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
   computed: {
      isCollapse() {
         return this.$store.state.navBar.isCollapse
      }
   }
};
</script>

<style lang="scss" scoped>
.header {
   position: sticky;
   top: 0;
   z-index: 2;
   background-color: #33589e;
   -webkit-box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
   box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
}

.asiderBar {
   position: sticky;
   top: 0;
   // background-color: #33589e;
   height: 100vh;
   -webkit-box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
   box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);

   // 添加响应式
   @media (max-width: 768px) {
      position: fixed;
      z-index: 1000;
   }
}

.slide-left-enter-active,
.slide-left-leave-active {
   transition: all 0.3s ease;
}

.slide-left-enter-from,
.slide-left-leave-to {
   transform: translateX(-100%);
   opacity: 0;
}
</style>
