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
        prop="小组号"
        label="小组"
        width="100">
      </el-table-column>

      <el-table-column
        fixed
        prop="选题号"
        label="选题"
        width="100">
      </el-table-column>

      <el-table-column
        fixed
        prop="报告链接"
        label="查看报告"
        width="100">
        <template slot-scope="scope">
          <el-link :href="tableData[scope.$index].报告链接" target="_blank">报告链接</el-link>
        </template>
      </el-table-column>

      <!--      <el-table-column-->
      <!--        label="填写进度"-->
      <!--        width="100">-->
      <!--        <el-input v-model="input1" placeholder="进度"></el-input>-->
      <!--        <el-divider></el-divider>-->
      <!--        <div style="text-align: center">-->
      <!--          <el-button type="primary" icon="el-icon-edit" @click="tianxie('input1')">确认</el-button>-->
      <!--        </div>-->
      <!--      </el-table-column>-->

      <!--这个地方是填写数据的，然后用scope这个就可以保证在有标签的时候依然可以绑定-->
      <!--{{tableData[scope.$index].进度}}就可以把进度显示在网页上-->
      <el-table-column
        prop="进度"
        label="进度"
        width="100">
        <template slot-scope="scope">
          <el-button type="text" @click="open1(scope.$index)">{{tableData[scope.$index].进度}}</el-button>
        </template>
      </el-table-column>

      <el-table-column
        prop="分数"
        label="分数"
        width="100">
        <template slot-scope="scope">
          <el-button type="text" @click="open2(scope.$index)">{{tableData[scope.$index].分数}}</el-button>
        </template>
      </el-table-column>

      <!--      <el-table-column-->
      <!--        label="批改分数"-->
      <!--        width="100">-->
      <!--        <el-input v-model="input2" placeholder="分数"></el-input>-->
      <!--        <el-divider></el-divider>-->
      <!--        <div style="text-align: center">-->
      <!--          <el-button type="primary" icon="el-icon-edit" @click="pigai">确认</el-button>-->
      <!--        </div>-->
      <!--      </el-table-column>-->

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
      tableData: [
        //！！！
        //这个地方是为了能在前端测试数据加的
        //和后端交互时需要删掉
        {
          小组号: '123',
          选题号: '456',
          报告链接:'https://www.baidu.com',
          进度:'111',
          分数:'12',
        },
        {
          小组号: '222',
          选题号: '000',
          报告链接:'https://www.csdn.net/',
          进度:'122',
          分数:'18',
        },

      ]
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
      request.get("/api/老师看到的选题情况")
        .then(res=>{
          this.tableData=res.data.results
        })
    },

    open1(a) {
      console.log(a)
      this.$prompt('请输入进度', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '进度是: ' + value
        });
        //！！！注意，这里是一个失败的例子
        //因为用前端写的话就需要其他几个都是对应的才行，因此我想用index来标记
        //但是实际上不能标记，至少我运行的时候会有问题
        //所以在想会不会直接调用sql语句就可以成功了
        //调整前端数据的例子在修改分数那里open2()
        this.$set(this.tableData,a,{
          //这个需要是和后端对应的才行
          小组号: tableData[a].小组号,
          选题号: tableData[a].选题号,
          报告链接:tableData[a].报告链接,
          进度:value,
          分数:tableData[a].分数,
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },

    open2(a) {
      console.log(a)
      this.$prompt('请输入分数', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '分数: ' + value
        });
        //这里是一个成功的例子，这个0代表着第一行数据
        //然后就点击第一行的分数就可以直接修改了
        this.$set(this.tableData,0,{
          //这个需要是和后端对应的才行
          小组号: '123',
          选题号: '456',
          报告链接:'https://baidu.com',
          进度:'111',
          分数:value,
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },

    // tianxie(input1){
    //   var qs = require('querystring')
    //   this.$refs[input1].validate((valid) => {
    //     if (valid) {
    //       request.post("/setProgress",qs.stringify(this.input1)).then(res=>{
    //         this.$message({
    //           message:res.data.msg
    //         })
    //       })
    //     }
    //   })
    // },

    // pigai(){
    //
    //   var qs = require('querystring')
    //   this.$refs[input2].validate((valid) => {
    //     if (valid) {
    //       request.post("/setGrade",qs.stringify(this.input2)).then(res=>{
    //         this.$message({
    //           message:res.data.msg
    //         })
    //       })
    //     }
    //   })
    // },

  }

}
</script>

<style scoped>

</style>
