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
        prop="题号"
        label="题号"
        width="100">
      </el-table-column>
      <el-table-column
        fixed
        prop="学习资料"
        label="学习资料"
        width="100">
      </el-table-column>

      <el-table-column
        fixed
        prop="下载地址"
        label="下载地址"
        width="100">
        <template slot-scope="scope">
          <el-link :href="tableData[scope.$index]['下载地址']" target="_blank">报告链接</el-link>
        </template>
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
  data(){
    request.post("/getResource").then(res=>{
      this.tableData = res.data.data
    })
    return {
  tableData: []
    }
  }
}

</script>

<style scoped>

</style>
