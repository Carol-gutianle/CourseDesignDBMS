<template>
  <div >
    <div style="height: 40px">
    </div>

    <div style="text-align: center;margin: auto">
      <i class="el-icon-edit-outline"></i>
      <p>选题情况</p>
    </div>

    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%">
      <el-table-column
        fixed
        prop="小组"
        label="小组"
        width="100">
      </el-table-column>

      <el-table-column
        fixed
        prop="选题"
        label="选题"
        width="100">
      </el-table-column>

      <el-table-column
        label="查看报告"
        width="100">
        <el-link href="https://element.eleme.io" target="_blank">报告链接</el-link>
      </el-table-column>

      <el-table-column
        label="填写进度"
        width="100">
        <el-input v-model="input1" placeholder="请填写进度"></el-input>
        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="tianxie('input1')">确认</el-button>
        </div>
      </el-table-column>

      <el-table-column
        label="批改分数"
        width="100">
        <el-input v-model="input2" placeholder="请批改分数"></el-input>
        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="pigai('input2')">确认</el-button>
        </div>
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
  name: "teacher_caozuo2",
  data() {
    return {
      input1:'',
      input2:''
    }
  },

  methods: {

    tianxie(input1){
      var qs = require('querystring')
      this.$refs[input1].validate((valid) => {
        if (valid) {
          request.post("/setProgress",qs.stringify(this.input1)).then(res=>{
            this.$message({
              message:res.data.msg
            })
          })
        }
      })
    },

    pigai(input2){
      var qs = require('querystring')
      this.$refs[input2].validate((valid) => {
        if (valid) {
          request.post("/setGrade",qs.stringify(this.input2)).then(res=>{
            this.$message({
              message:res.data.msg
            })
          })
        }
      })
    },





  }

}
</script>

<style scoped>

</style>
