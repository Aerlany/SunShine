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
                  @click="handleAdd"
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
            <el-table-column label="用户名" prop="projectCode" width="120" />
            <el-table-column
               label="密码"
               prop="projectName"
               :show-overflow-tooltip="true"
               width="200"
            />
            <el-table-column
               label="账号状态"
               prop="projectLeader"
               :show-overflow-tooltip="true"
               width="100"
            />
            <el-table-column
               label="用户类型"
               prop="projectLeader"
               :show-overflow-tooltip="true"
               width="100"
            />
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
                     >修改</el-button
                  >
                  <el-button
                     size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     >删除</el-button
                  >
                  <el-button
                     size="mini"
                     type="text"
                     icon="el-icon-d-arrow-right"
                     @click="handleMore(scope.row)"
                     >更多</el-button
                  >
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
   </div>
</template>

<script>
import RightToolbar from "@/components/RightToolbar";

export default {
   data() {
      return {
         //表格相关参数
         loading: false,
         tableData: [{}, {}],
         showSearch: true,

         //分页相关参数
         pageSize: 10,
         count: 100,
      };
   },
   methods: {
      //获取表格数据
      getTableData() {},
      handleSelectionChange() {},
      //表格数据变更方法
      handleAdd() {},
      handleDelete() {},
      handleUpdate() {},
      handleExport() {},

      //分页变更
      handlePageChange() {},
   },
   components: { RightToolbar },
};
</script>

<style></style>
