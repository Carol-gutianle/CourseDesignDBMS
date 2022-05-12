<template>
  <div >
    <div style="height: 40px">
    </div>

    <div style="text-align: center;margin: auto">
      <i class="el-icon-tickets"></i>
      <p>题库内容</p>
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
        prop="题目"
        label="题目"
        width="500">
      </el-table-column>


    </el-table>

<!--    <div style="margin: 10px 0">-->
<!--      <el-pagination-->
<!--        @size-change="handleSizeChange"-->
<!--        @current-change="handleCurrentChange"-->
<!--        :current-page="currentPage4"-->
<!--        :page-sizes="[5, 10, 15, 20]"-->
<!--        :page-size="pageSize"-->
<!--        layout="total, sizes, prev, pager, next, jumper"-->
<!--        :total="total">-->
<!--      </el-pagination>-->
<!--    </div>-->


  </div>
</template>

<script>
import request from "../util/request";

export default {
  name: "tiku",
  data() {
    request.post("/seleQueName").then(res=>{
      console.log(res.data)
      this.tableData = res.data.data
    })
    return {
      tableData: this.tableData
    };
  },

  methods: {
    save(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          request.post("/seleQueName").then(res => {
            console.log(res.data); //打印出来
          })
        }
      });
    },
  }

}
</script>

<style scoped>

</style>
