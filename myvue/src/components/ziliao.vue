<template>
  <div >
    <div style="height: 40px">
    </div>

    <div style="text-align: center;margin: auto">
      <i class="el-icon-s-order"></i>
      <p>资料内容</p>
    </div>

    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%">
      <el-table-column
        fixed
        prop="资料编号"
        label="资料编号"
        width="100">
      </el-table-column>
      <el-table-column
        fixed
        prop="资料内容"
        label="资料内容"
        width="100">
      </el-table-column>

      <el-table-column
        label="下载"
        width="100">
        <el-link href="https://element.eleme.io" target="_blank">下载链接</el-link>
      </el-table-column>



    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>


  </div>
</template>

<script>
import request from "../util/request";

export default {
  name: "ziliao",
}

</script>

<style scoped>

</style>
