<template>
  <div >
    <div style="height: 40px">
    </div>
    <div style="text-align: center;width: 100%; padding-left: 400px;margin: auto">

      <el-card style="width: 40%; margin: 10px">
        <i class="el-icon-tickets"></i>
        <p>查看学生选题情况</p>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="save('form')">查看</el-button>
        </div>

        <el-divider></el-divider>
        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="gocaozuo2">!进阶2</el-button>
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

          <el-form-item label="tpass" :label-width="formLabelWidth" prop="tpass">
            <el-input style="width: 200px"
                      placeholder="请输入新密码"
                      v-model="form.tpass"
                      clearable>
            </el-input>
          </el-form-item>

          <div style="margin: 50px;"></div>
        </el-form>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="save('form')">确认</el-button>
        </div>

      </el-card>


    </div>
  </div>
</template>

<script>
import request from "../util/request";

export default {
  name: "teacher_caozuo",
  data() {
    return {
      form:{
        kno:'',
        tpass:'',
      },
      search: '',
    };
  },

  methods: {

    gocaozuo2(){
      this.$router.push({
        path:'../teacher_caozuo2'
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
