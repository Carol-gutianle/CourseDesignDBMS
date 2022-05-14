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

        <i class="el-icon-lock"></i>
        <p>上传题目</p>

        <el-form :model="form2"  class="demo-ruleForm" label-position="left" label-width="80px"  status-icon :rules="rules" ref="form2">
          <el-form-item label="题目" label-width="formLabelWidth" prop="qname">
            <el-input style="width: 200px"
                      placeholder="请输入题目"
                      v-model="form2.qname"
                      clearable>
            </el-input>
          </el-form-item>

          <div style="margin: 50px;"></div>
        </el-form>

        <div style="text-align: center">
          <el-button type="primary" icon="el-icon-edit" @click="qsubmit('form2')">提交</el-button>
        </div>

      </el-card>


      <el-card style="width: 40%; margin: 10px">
        <p>上传资料</p>
        <el-form :model="newform1"  class="demo-ruleForm" label-position="left" label-width="80px"  status-icon :rules="rules" ref="newform1">
          <el-form-item label="上传文件"  prop="uploadfile">
            <el-upload
              class="upload-demo"
              ref="upload"
              action="/teaUpload"
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
          <el-form-item label="题号" label-width="formLabelWidth" prop="tno2">
            <el-input style="width: 200px"
                      placeholder="请输入题号"
                      v-model="newform1.tno2"
                      clearable>
            </el-input>
          </el-form-item>
          <el-form-item label="文件名" label-width="formLabelWidth" prop="tname">
            <el-input style="width: 200px"
                      placeholder="请输入文件名"
                      v-model="newform1.tname"
                      clearable>
            </el-input>
          </el-form-item>
          <el-button type="primary" @click="submituploadform('newform1')">提交</el-button>
        </el-form>
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
var checktno = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入题号'));
  }
  else {callback();}
};
var checktname = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入文件名'));
  }
  else {callback();}
};
var checkqname = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请输入题目'));
  }
  else {callback();}
};
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
      /*---------------------------------lxt 文件上传----------------------------------*/
      fileList: [],
      fd: {},
      newform1:{
        tno2:'',
        tname:'',
      },
      form2:{
        qname:'',
      },
      /*---------------------------------lxt 文件上传----------------------------------*/
      rules: {
        tpass:[ { validator: checktpass, trigger: 'blur'  } ],
        /*---------------------------------lxt 文件上传----------------------------------*/
        tno2:[ { validator: checktno, trigger: 'blur'  } ],
        qno:[ { validator: checktno, trigger: 'blur'  } ],
        tname:[ { validator: checktname, trigger: 'blur'  } ],
        qname:[ { validator: checkqname, trigger: 'blur'  } ],
        /*---------------------------------lxt 文件上传----------------------------------*/
      }
    };
  },
  methods: {
    /*---------------------------------lxt -----------------------------------------------*/
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
      this.fd.append('tno', this.newform1.tno2)
      this.fd.append('tname', this.newform1.tname)

      request.post("/teaUpload", this.fd).then(res=>{
        if(res.data.code===200) {
          this.$message({
            type: "success",
            message: "上传成功！"
          })
          this.newform1.tno2 = ''
          this.newform1.tname = ''
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
      this.tno2=''
      this.tname=''
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },

    /*老师提交题目*/
    qsubmit(form2){
      var qs = require('querystring')
      this.$refs[form2].validate((valid) => {
        if (valid) {
          request.post("/uploadQue",qs.stringify(this.form2)).then(res=>{
            this.$message({
              message:res.data.msg
            })
          })
        }
      })
    },

    /*---------------------------------lxt ----------------------------------------------*/
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
            this.form.tpass=''
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
