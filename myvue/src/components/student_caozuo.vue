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

        <i class="el-icon-tickets"></i>
        <p>查看资料</p>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="goziliao">查看资料</el-button>
        </div>


      </el-card>

      <!--                                         lxt                                      -->
      <el-card style="width: 40%; margin: 10px">
        <p>上传报告</p>
        <el-form :model="newform1"  class="demo-ruleForm" label-position="left" label-width="80px"  status-icon :rules="rules" ref="newform">
          <el-form-item label="上传文件"  prop="uploadfile">
            <el-upload
              class="upload-demo"
              ref="upload"
              action="/api/upload"
              :http-request="httprequest"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              multiple
              :file-list="fileList"
              :data="newform1"
              :auto-upload="false"
              :on-change="getfile"
              name="uploadfile"
            >
              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>

            </el-upload>
          </el-form-item>
          <el-button type="primary" @click="submituploadform('newform1')">提交</el-button>
        </el-form>
      </el-card>
      <!--                                         lxt                                      -->

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

//检查
var checkth = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入题号'));
  }
  else {callback();}
};

export default {
  name: "student_caozuo",
  data() {

    var qs = require('querystring');
    var cursno = this.$route.query.sno
    var curqno = this.$route.query.qno
    var snoform = {sno:cursno}
    request.post("/seleGrade",qs.stringify(snoform)).then(res=>{
      this.value = res.data.data["分数"]
      console.log(this.value)
    })
    /*获取题号*/

    return {
      /*---------------------------------lxt 文件上传----------------------------------*/
      fileList: [],
      fd: {},
      newform1:{

        th: curqno
      },
      /*---------------------------------lxt 文件上传----------------------------------*/


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
        th:[ { validator: checkth, trigger: 'blur'  } ],
      }
    };
  },



  methods: {
    /*---------------------------------lxt 文件上传----------------------------------*/
    getfile(file, fileList) {
      const fd = new FormData()// FormData 对象
      this.fd = fd
    },
    httprequest(param) {
      const fileObj = param.file // 相当于input里取得的files
      this.fd.append('file', fileObj)// 文件对象
    },
    submituploadform(file) {
      this.$refs.upload.submit()
      this.fd.append('th', this.newform1.th)

      // uploadAnnex(this.fd).then(res => {
      request.post("/api/upload", this.fd).then(res=>{
        if(res.data.code===200) {
          this.$message({
            type: "success",
            message: "上传成功！"
          })
        }
        else {
          this.$message({
            type: "error",
            message: "上传失败！"
          })
        }
      })
      this.fd = {}
      this.fileList = []
      this.th=''
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    /*---------------------------------lxt 文件上传----------------------------------*/



    isLeader() {
      this.isleader = !this.isleader;
    },

    newpass(newform){
      var qs = require('querystring')
      this.$refs[newform].validate((valid) => {
        if (valid) {
          request.post("/stuUpdatePwd",qs.stringify(this.newform)).then(res=>{
            this.$message({
              type: "success",
              message:"更新成功！"
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
    goziliao(){
      this.$router.push({
        path:'../ziliao'
      })
    },
    submit(form) {
      this.$refs[form].validate((valid) => {
        var qs = require('querystring')
        if(valid) {
          request.post("/chooseQue",qs.stringify(this.form)).then(res=>{
            if(res.data.code===200) {
              this.$message({
                type: "success",
                message: "上传成功！"
              })
            }
            else {
              this.$message({
                type: "error",
                message: "上传失败！"
              })
            }
          })
        }
      })
    }
  }

}
</script>

<style scoped>

</style>
