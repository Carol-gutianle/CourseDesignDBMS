<template>
  <div >
    <div style="height: 40px">
    </div>
    <div style="text-align: center;width: 100%; padding-left: 400px;margin: auto">

      <el-card style="width: 40%; margin: 10px">
        <i class="el-icon-tickets"></i>
        <p>查看学生选题情况</p>
        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="gocaozuo2">查看</el-button>
        </div>

      </el-card>

      <el-card style="width: 40%; margin: 10px">

        <i class="el-icon-link"></i>
        <p>资料</p>

        <el-upload
          class="upload-demo"
          drag
          action="https://jsonplaceholder.typicode.com/posts/"
          multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        </el-upload>
      </el-card>

      <el-card style="width: 40%; margin: 10px">

        <i class="el-icon-lock"></i>
        <p>修改密码</p>

        <el-form :model="form"  class="demo-ruleForm" label-position="left" label-width="80px"  status-icon :rules="rules" ref="form">
          <el-form-item label="新密码" label-width="formLabelWidth" prop="tpass">
            <el-input style="width: 200px"
                      placeholder="请输入新密码"
                      v-model="form.tpass"
                      clearable>
            </el-input>
          </el-form-item>

          <div style="margin: 50px;"></div>
        </el-form>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="newpass('form')">确认</el-button>
        </div>

      </el-card>


    </div>
  </div>
</template>

<script>
import request from "../util/request";
//检查密码
var checktpass = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入密码'));
  }
  else {callback();}
};
export default {
  name: "teacher_caozuo",
  data() {
    var curtno = this.$route.query.tno
    return {
      form:{
        tno: curtno,
        tpass:'',
      },
      rules: {
        tpass:[ { validator: checktpass, trigger: 'blur'  } ],
      }
    };
  },
  methods: {
    gocaozuo2(){
      this.$router.push({
        path:'../teacher_caozuo2'
      })
    },
    //密码修改
    newpass(form){
      var qs = require('querystring')
      this.$refs[form].validate((valid) => {
        if (valid) {
          request.post("/teaUpdatePwd",qs.stringify(this.form)).then(res=>{
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
