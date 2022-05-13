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
      @row_click="onRowclick"
      style="width: 100%">
      <el-table-column
        fixed
        prop="题号"
        label="题号"
        width="100">
      </el-table-column>

      <el-table-column
        fixed
        prop="小组长"
        label="小组长"
        width="100">
      </el-table-column>

      <el-table-column
        fixed
        prop="工作报告"
        label="工作报告"
        width="100">
        <template slot-scope="scope">
          <el-link :href="tableData[scope.$index]['工作报告']" target="_blank">报告链接</el-link>
        </template>
      </el-table-column>

      <!--这个地方是填写数据的，然后用scope这个就可以保证在有标签的时候依然可以绑定-->
      <!--{{tableData[scope.$index].进度}}就可以把进度显示在网页上-->
      <el-table-column
        prop="进度情况"
        label="进度情况"
        width="100">
        <template slot-scope="scope">
          <el-button type="text" @click="open1(scope.$index)">{{tableData[scope.$index]["进度情况"]}}</el-button>
        </template>
      </el-table-column>

      <el-table-column
        prop="分数"
        label="分数"
        width="100">
        <template slot-scope="scope">
          <el-button type="text" @click="open2(scope.$index)">{{tableData[scope.$index]["分数"]}}</el-button>
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
  name: "teacher_caozuo2",
  data() {
    return {
      input1:'',
      input2:'',
      tableData: []
    }
  },

  created() {
    this.load()
  },

  methods: {
    onRowclick(row, column, event){
      console.log(row, column, event)
    },
    //加载表格信息
    load(){
      request.get("/seleGData")
        .then(res=>{
          console.log(res.data.data)
          this.tableData=res.data.data
        })
    },

    setGrade(a){
      var qs = require('querystring')
      var gradeform = {qno:this.tableData[a]["题号"],gleader:this.tableData[a]["小组长"],grade:this.tableData[a]["分数"]}
      request.post('/setGrade',qs.stringify(gradeform))
      .then(res=>{
        this.$message(res.data.msg)
      })
    },

    setProgress(a){
      var qs = require('querystring')
      var progressform = {qno:this.tableData[a]["题号"],gleader:this.tableData[a]["小组长"],grade:this.tableData[a]["进度情况"]}
      request.post('/setProgress',qs.stringify(progressform))
        .then(res=>{
          this.$message(res.data.msg)
        })
    },

    open1(a) {
      let qs = require('querystring')
      this.$prompt('请输入进度', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({value}) => {
        this.tableData[a]["进度情况"] = value
        this.setProgress(a)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },

    open2(a) {
      let qs = require('querystring')
      // var that = this
      // var gradeform = {qno:this.tableData[a]["题号"],grade:0};
      this.$prompt('请输入分数', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.tableData[a]["分数"] = value
        this.setGrade(a)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
  }

}
</script>

<style scoped>

</style>
