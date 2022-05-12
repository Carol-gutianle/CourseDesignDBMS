<template>
  <div >
    <div style="height: 40px">
    </div>
    <div style="text-align: center;width: 100%; padding-left: 400px;margin: auto">
      <el-card style="width: 40%; margin: 10px">
        <el-form :model="form"  class="demo-ruleForm" label-position="left" label-width="80px"  status-icon :rules="rules" ref="form">

          <i class="el-icon-user-solid"></i>
          <p>学生登录</p>

          <el-form-item label="学号" label-width="formLabelWidth" prop="sno">
            <el-input style="width: 200px"
                      placeholder="请输入姓名"
                      v-model="form.sno"
                      clearable>
            </el-input>
          </el-form-item>

          <el-form-item label="密码" label-width="formLabelWidth" prop="spwd" >
            <el-input style="width: 200px"
                      placeholder="请输入密码"
                      v-model="form.spwd"
                      clearable>
            </el-input>
          </el-form-item>

          <div style="margin: 50px;"></div>
        </el-form>
        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="save('form')">登录</el-button>
        </div>

      </el-card>
    </div>
  </div>
</template>

<script>
import request from "../util/request";
//检查姓名
var checksno = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入名字'));
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

export default {
  data() {
    return {
      form:{
        sno: '',
        spwd: '',
      },
      rules: {
        sno:[ { validator: checksno, trigger: 'blur'  } ],
        spwd:[ { validator: checkspwd, trigger: 'blur'  } ]
      }
    };
  },

  methods: {
    gocaozuo(){
      this.$router.push({
        path:'../student_caozuo'
      })
    },
    save(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          var qs = require('querystring')
          console.log(this.form)
          request.post("/stuLogin", qs.stringify(this.form)).then(res => {
            if(res.data.code===200) {
              this.gocaozuo()
            } else {
              this.$message({
                type: "error",
                message: "账号或密码错误！"
              })
            }
          })
        }
        else {
          console.log('error')
        }
      });
    },
  }

}
</script>

<style scoped>

</style>
