webpackJsonp([3],{Efrj:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a("RRo+"),n=a.n(r),o=a("D/cR"),s={name:"teacher_denglu",data:function(){return{form:{tno:"",tname:"",tpass:""},search:"",rules:{tname:[{validator:function(e,t,a){t?a():a(new Error("请输入名字"))},trigger:"blur"}],tno:[{validator:function(e,t,a){t||a(),setTimeout(function(){n()(t)?a():a(new Error("请输入数字"))},1e3)},trigger:"blur"}],tpass:[{validator:function(e,t,a){t?a():a(new Error("请输入密码"))},trigger:"blur"}]}}},methods:{save:function(e){var t=this;this.$refs[e].validate(function(e){e&&o.a.post("/api/person/addPerson",t.form).then(function(e){console.log(e.data),0!==e.data?1===e.data?t.$message({type:"success",message:"成功添加"+t.form.name}):t.$confirm("用户名"+t.form.username+"已存在，需要修改信息吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){o.a.put("/api/person/updatePerson",t.form).then(function(e){console.log(e),"0"!==e.code&&t.$message({type:"success",message:"修改成功"})})}).catch(function(){t.$message({type:"info",message:"已取消修改"})}):t.$message({type:"error",message:"名称"+t.form.name+"重复,请重新填写"})})})}}},l={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",{staticStyle:{height:"40px"}}),e._v(" "),a("div",{staticStyle:{"text-align":"center",width:"100%","padding-left":"400px",margin:"auto"}},[a("el-card",{staticStyle:{width:"40%",margin:"10px"}},[a("el-form",{ref:"form",staticClass:"demo-ruleForm",attrs:{model:e.form,"label-position":"left","label-width":"80px","status-icon":"",rules:e.rules}},[a("i",{staticClass:"el-icon-user-solid"}),e._v(" "),a("p",[e._v("老师登录")]),e._v(" "),a("el-form-item",{attrs:{label:"tno","label-width":e.formLabelWidth,prop:"tno"}},[a("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入工资号",clearable:""},model:{value:e.form.tno,callback:function(t){e.$set(e.form,"tno",t)},expression:"form.tno"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"tname","label-width":e.formLabelWidth}},[a("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入姓名",clearable:""},model:{value:e.form.tname,callback:function(t){e.$set(e.form,"tname",t)},expression:"form.tname"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"tpass","label-width":e.formLabelWidth}},[a("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入密码",clearable:""},model:{value:e.form.tpass,callback:function(t){e.$set(e.form,"tpass",t)},expression:"form.tpass"}})],1),e._v(" "),a("div",{staticStyle:{margin:"50px"}})],1),e._v(" "),a("div",{staticStyle:{"text-align":"center"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-edit"},on:{click:function(t){return e.save("form")}}},[e._v("登录")])],1)],1)],1)])},staticRenderFns:[]};var i=a("VU/8")(s,l,!1,function(e){a("o8ra")},"data-v-fee61886",null);t.default=i.exports},o8ra:function(e,t){}});
//# sourceMappingURL=3.5079236656b739efc021.js.map