<template>
   <div>
      <el-card shadow="hover">
         <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
               <el-button
                  type="primary"
                  plain
                  icon="el-icon-plus"
                  size="mini"
                  @click="open = !open"
                  >新增</el-button
               >
            </el-col>
            <el-col :span="1.5">
               <el-button
                  type="danger"
                  plain
                  icon="el-icon-delete"
                  size="mini"
                  @click="handleDelete"
                  >删除</el-button
               >
            </el-col>
            <el-col :span="1.5">
               <el-button
                  type="warning"
                  plain
                  icon="el-icon-download"
                  size="mini"
                  @click="handleExport"
                  >导出</el-button
               >
            </el-col>
            <el-col :span="1.5">
               <RightToolbar
                  :showSearch.sync="showSearch"
                  @queryTable="getTableData"
               ></RightToolbar>
            </el-col>
         </el-row>

         <el-table
            v-loading="loading"
            :data="tableData"
            @selection-change="handleSelectionChange"
         >
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="用户名" prop="userName" width="200" />
            <el-table-column
               label="账号状态"
               :show-overflow-tooltip="true"
               width="100"
            >
               <template slot-scope="scope">
                  <el-tag v-if="scope.row.status == 0" type="success"
                     >启用</el-tag
                  >
                  <el-tag v-if="scope.row.status == 1" type="dange"
                     >停用</el-tag
                  >
               </template>
            </el-table-column>
            <el-table-column
               label=" 用户类型"
               prop="userType"
               :show-overflow-tooltip="true"
               width="100"
            >
               <template slot-scope="scope">
                  <el-tag v-if="scope.row.status == 0">管理员</el-tag>
                  <el-tag v-if="scope.row.status == 1">用户</el-tag>
               </template></el-table-column
            >
            <el-table-column
               fixed="right"
               label="操作"
               align="center"
               class-name="small-padding fixed-width"
            >
               <template slot-scope="scope">
                  <el-button
                     size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     >停用</el-button
                  >
                  <el-button
                     size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     >删除</el-button
                  >
                  <el-dropdown size="mini">
                     <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-d-arrow-right"
                        >更多</el-button
                     >
                     <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item
                           command="handleResetPwd"
                           icon="el-icon-key"
                           >前往主页</el-dropdown-item
                        >
                        <el-dropdown-item
                           command="handleResetPwd"
                           icon="el-icon-key"
                           >权限设置</el-dropdown-item
                        >
                        <el-dropdown-item
                           command="handleResetPwd"
                           icon="el-icon-key"
                           >密码重置</el-dropdown-item
                        >
                     </el-dropdown-menu>
                  </el-dropdown>
               </template>
            </el-table-column>
         </el-table>
         <center>
            <el-pagination
               :page-size="pageSize"
               @current-change="handlePageChange"
               background
               layout="prev, pager, next"
               :total="count"
            >
            </el-pagination>
         </center>
      </el-card>

      <!-- 项目新增和修改弹窗 -->
      <el-dialog
         :title="title"
         :visible.sync="open"
         width="500px"
         append-to-body
      >
         <el-form
            ref="form"
            :model="form"
            :rules="rules"
            label-position="right"
            label-width="100px"
         >
            <el-form-item label="用户名" prop="userName">
               <el-input v-model="form.userName" placeholder="请输入用户" />
            </el-form-item>
            <!-- <el-form-item label="密码" prop="password">
                    <el-input v-model="form.password" placeholder="请输入密码" />
                </el-form-item> -->
            <el-form-item label="账号状态" prop="status">
               <el-select v-model="form.status" placeholder="请选择账号状态">
                  <el-option label="正常" :value="0"></el-option>
                  <el-option label="停用" :value="1"></el-option>
               </el-select>
            </el-form-item>
            <el-form-item label="权限设置" prop="userType">
               <el-select v-model="form.userType" placeholder="请选择账号状态">
                  <el-option label="管理员" :value="0"></el-option>
                  <el-option label="用户" :value="1"></el-option>
               </el-select>
            </el-form-item>
         </el-form>
         <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="handleAdd">确 定</el-button>
            <el-button @click="open = false">取 消</el-button>
         </div>
      </el-dialog>
   </div>
</template>

<script>
import RightToolbar from "@/components/RightToolbar";

export default {
   data() {
      return {
         //表格相关参数
         loading: false,
         tableData: [],
         showSearch: true,

         //分页相关参数
         pageSize: 10,
         currentPage: 1,
         count: "",

         //添加弹出层控件
         open: false,
         form: {},
         rules: [],

         //删除功能
         deleteArray: [],
      };
   },
   methods: {
      //获取表格数据
      getTableData() {
         this.loading = true;
         this.api.get(
            "/user/getByPage/" + this.currentPage + "/" + this.pageSize,
            "",
            (res) => {
               const { flag, data, msg } = res;
               if (flag) {
                  this.tableData = data;
                  this.count = msg;
               } else {
                  this.$modal.msgError(msg);
               }
               setTimeout(() => {
                  this.loading = false;
               }, 200);
            }
         );
      },
      handleSelectionChange() {},

      //表格数据变更方法
      handleAdd() {
         this.api.put("/user/addUser", this.form, (res) => {
            const { flag, data, msg } = res;
            if (flag) {
               this.tableData = data;
               this.open = false;
            } else {
               this.$modal.msgError(msg);
            }
            setTimeout(() => {
               this.loading = false;
            }, 200);

            this.getTableData();
         });
      },
      handleDelete(data) {
         this.deleteArray.push(data.id);
         this.submitDelete();
      },
      submitDelete() {
         this.$modal
            .confirm("是否删除？")
            .then(() => {
               this.api.del("/user/deleteAll", this.deleteArray, (res) => {
                  const { flag, data, msg } = res;
                  if (flag) {
                     this.$modal.msgSuccess("删除成功");
                     this.deleteArray = [];
                  } else {
                     this.$modal.msgError(msg);
                  }
                  this.getTableData();
               });
            })
            .catch(() => {
               this.deleteArray = [];
               this.$modal.msg("已取消");
            });
      },
      handleUpdate() {},
      handleExport() {},
      //分页变更
      handlePageChange() {},
   },
   mounted() {
      this.getTableData();
      this.$store.commit("RESET_topBarDisPlayTags");
      this.$store.commit("PUSH_RESET_topBarDisPlayTags", "用户管理");
   },
   components: { RightToolbar },
};
</script>

<style></style>
