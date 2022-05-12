<template>
  <div >
    <div style="height: 40px">
    </div>
    <div style="text-align: center;width: 100%; padding-left: 400px;margin: auto">

      <el-card style="width: 40%; margin: 10px">

        <el-form :model="form"  class="demo-ruleForm" label-position="left" label-width="80px"  status-icon :rules="rules" ref="form">

          <i class="el-icon-user"></i>
          <p>选题申请</p>

          <el-form-item label="学号" label-width="formLabelWidth" prop="sno">
            <el-input style="width: 200px"
                      placeholder="请输入学号"
                      v-model="form.sno"
                      clearable>
            </el-input>
          </el-form-item>

          <el-form-item label="组长">
            <el-checkbox :indeterminate="isleader" @change="isLeader">是否担任组长</el-checkbox>
          </el-form-item>

          <div style="margin: 50px;"></div>
        </el-form>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="submit('form')">提交</el-button>
        </div>

      </el-card>

      <el-card style="width: 40%; margin: 10px">

        <i class="el-icon-tickets"></i>
        <p>选题情况查询</p>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="runToQue()">查看全部</el-button>
        </div>

      </el-card>

      <el-card style="width: 40%; margin: 10px">

        <i class="el-icon-document-add"></i>
        <p>上传报告</p>
        <form method="post" action="http://localhost:8080/upload" enctype="multipart/form-data">
          选择要上传的文件：<input type="file" name="file"><br>
          <hr>
          <input type="submit" value="提交">
        </form>
      </el-card>

      <el-card style="width: 40%; margin: 10px">

        <i class="el-icon-lock"></i>
        <p>修改密码</p>

        <el-form :model="newform"  class="demo-ruleForm" label-position="left" label-width="80px"  status-icon :rules="rules" ref="newform">

          <el-form-item label="新密码" label-width="formLabelWidth" prop="nspwd">
            <el-input style="width: 200px"
                      placeholder="请输入新密码"
                      v-model="newform.nspwd"
                      clearable>
            </el-input>
          </el-form-item>

          <div style="margin: 50px;"></div>
        </el-form>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="newpass('newform')">确认</el-button>
        </div>

      </el-card>

      <el-card style="width: 40%; margin: 10px">

        <i class="el-icon-trophy"></i>
        <p>成绩</p>
        <el-rate
          v-model="value"
          disabled
          show-score
          text-color="#ff9900"
          score-template="{value}">
        </el-rate>
      </el-card>


    </div>
  </div>
</template>

<script>
import request from "../util/request";
//检查学号
var checksno = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入学号'));
  }
  else {callback();}
};
//检查密码
var checkspwd = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入密码'));
  }
  else {callback();}
};

//检查密码
var checknspwd = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入密码'));
  }
  else {callback();}
};

export default {
  name: "student_caozuo",
  data() {
    var qs = require('querystring');
    var cursno = this.$route.query.sno
    var snoform = {sno:cursno}
    request.post("/seleGrade",qs.stringify(snoform)).then(res=>{
      this.value = res.data.data["分数"]
      console.log(this.value)
    })
    return {
      isleader: false,
      cursno: cursno,
      newform:{
        sno: cursno,
        nspwd: ''
      },
      form:{
        sno: this.cursno,
        sname: '',
        sclass:'',
        sleader:'',
        kno:'',
        spass:'',
      },
      search: '',
      value: this.value,//这里返回成绩
      rules: {
        sno:[ { validator: checksno, trigger: 'blur'  } ],
        spwd:[ { validator: checkspwd, trigger: 'blur'  } ],
        nspwd:[ { validator: checknspwd, trigger: 'blur'  } ],
      }
    };
  },



  methods: {

    isLeader() {
      this.isleader = !this.isleader;
    },

    newpass(newform){
      var qs = require('querystring')
      this.$refs[newform].validate((valid) => {
        if (valid) {
          request.post("/stuUpdatePwd",qs.stringify(this.newform)).then(res=>{
            this.$message({
              message:res.data.msg
            })
          })
        }
      })
    },

    runToQue(){
      this.$router.push({
        path:'../tiku'
      })
    },

    submit(form) {
      this.$refs[form].validate((valid) => {
        var qs = require('querystring')
        if(valid) {
          request.post("/chooseQue",qs.stringify(this.form)).then(res=>{
            this.$message({
              message:res.data.msg
            })
          })
        }
      })
    },



    save(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {

          request.post("/api/person/addPerson", this.form).then(res => {

            console.log(res.data); //打印出来
            if ((res.data !== 0)) {
              if ((res.data === 1)){
                this.$message({
                  type: "success",
                  message: "成功添加"+this.form.name
                })
              }else{
                this.$confirm("用户名"+this.form.username+"已存在，需要修改信息吗？", '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  request.put("/api/person/updatePerson",this.form).then(res=> {
                    console.log(res) //打印出来
                    if (res.code !== '0') {
                      this.$message({
                        type: "success",
                        message: "修改成功"
                      })

                    }
                  });

                }).catch(() => {
                  this.$message({
                    type: 'info',
                    message: '已取消修改'
                  });
                });
              }

            } else {
              this.$message({
                type: "error",
                message: "名称"+this.form.name+"重复,请重新填写"
              })
            }
          })
        }
      });
    },
  }

}
</script>

<style scoped>

</style>
