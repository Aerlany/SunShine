<template>
   <div>
      <div class="head_container">
         <div class="left_container">
            <el-button type="text" icon="el-icon-s-fold" @click="changeCollapse"></el-button>
            <!-- <el-breadcrumb separator="/" class="NavBar">
               <transition appear name="animate__animated animate__bounce" enter-active-class="animate__fadeInRight"
                  leave-active-class="animate__fadeOutLeft">
                  <div v-for="item in topBarDisPlayTags" :key="item">
                     <el-breadcrumb-item>
                        {{ item }}
                     </el-breadcrumb-item>
                  </div>
               </transition>
            </el-breadcrumb> -->
         </div>

         <div class="center_container">Sunshine 项目管理系统</div>
         <div class="right_container">
            <el-button type="text" icon="el-icon-search" @click="fullScreen()"
               style="margin-right: 5px;color: aliceblue;"></el-button>
            <el-button type="text" icon="el-icon-full-screen" @click="fullScreen()"
               style="margin-right: 10px;color: aliceblue;"></el-button>
            <el-dropdown>
               <span class="el-dropdown-link">
                  <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
               </span>
               <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>UserInfo</el-dropdown-item>
                  <el-dropdown-item divided @click.native="logout">
                     <span>退出登录</span>
                  </el-dropdown-item>
               </el-dropdown-menu>
            </el-dropdown>
         </div>
      </div>
   </div>
</template>

<script>
import screenfull from "screenfull";
export default {
   data() {
      return {
         tags: [{ name: "首页", isCurrent: "true" }, { name: "其他" }],
      };
   },
   methods: {
      changeCollapse() {
         this.$store.commit("SET_ISCOLLAPSE");
      },
      fullScreen() {
         screenfull.toggle();
      },
      async logout() {
         this.$confirm("确定注销并退出系统吗？", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
         })
            .then(() => {
               this.$store
                  .dispatch("LogOut")
                  .then(() => {
                     location.href = "/login";
                  })
                  .catch(() => { });
            })
            .catch(() => { });
      },
   },
   computed: {
      topBarDisPlayTags() {
         return this.$store.state.navBar.topBarDisPlayTags;
      },
   },
};
</script>

<style lang="scss">
.head_container {
   display: flex;
   margin-top: 5px;
   justify-content: space-between;
   align-items: center;
   margin-bottom: 3px;
   border-bottom: 1px solid rgba($color: #000000, $alpha: 0.1);
}

.left_container {
   display: flex;
   justify-content: space-between;
   align-items: center;

   .NavBar {
      margin-left: 10px;
      color: #fff;
   }
}

.center_container {
   display: flex;
   color: #fff;
}

.right_container {
   display: flex;
   justify-content: space-between;
   align-items: center;
}

.tags {
   display: flex;
   width: 100%;
   height: 20px;

   .el-tag {
      margin-right: 3px;
   }
}
</style>
