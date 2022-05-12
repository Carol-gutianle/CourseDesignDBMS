package com.hnu.dbserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnu.dbserver.mapper.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class Handler {
    @Autowired
    DataBase db;

    /*学生登录*/
    @PostMapping("/stuLogin")
    public com.alibaba.fastjson.JSONObject stuLogin(@RequestParam("sno") String sno,@RequestParam("spwd") String spwd) {
        com.alibaba.fastjson.JSONObject response = new JSONObject();
        if(db.stuLogin(sno,spwd).size()!=0){
            response.put("code",200);
            response.put("msg","登录成功");
            response.put("type","学生");
        }
        else {
            response.put("code",404);
            response.put("msg","登录失败");
            response.put("type","学生");
        }
        return response;
    }

    /*学生修改密码*/
    @ResponseBody
    @RequestMapping("stuUpdatePwd")
    public Map stuUpdatePwd(@RequestParam String sno, String nspwd) {
        HashMap<String,Object> response = new HashMap<String,Object>();
        if(db.stuUpdatePwd(sno,nspwd)!=0){
            response.put("code",200);
            response.put("msg","修改密码成功");
            response.put("type","学生");
            return response;
        }
        else {
            response.put("code",404);
            response.put("msg","修改密码失败");
            response.put("type","学生");
            return response;
        }
    }

    /*教师登录*/
    @ResponseBody
    @RequestMapping("teaLogin")
    public Map TeaLogin(@RequestParam String tno, String tpwd) {
        HashMap<String,Object> response = new HashMap<String,Object>();
        if(db.teaLogin(tno,tpwd).size()!=0){
            response.put("code",200);
            response.put("msg","登录成功");
            response.put("type","教师");
            return response;
        }
        else {
            response.put("code",404);
            response.put("msg","登录失败");
            response.put("type","教师");
            return response;
        }
    }

    /*教师修改密码*/
    @ResponseBody
    @RequestMapping("teaUpdatePwd")
    public Map teaUpdatePwd(@RequestParam String tno, String ntpwd) {
        HashMap<String,Object> response = new HashMap<String,Object>();
        if(db.teaUpdatePwd(tno,ntpwd)!=0){
            response.put("code",200);
            response.put("msg","修改密码成功");
            response.put("type","教师");
            return response;
        }
        else {
            response.put("code",404);
            response.put("msg","修改密码失败");
            response.put("type","教师");
            return response;
        }
    }

    /*学生查询选题情况*/
    @ResponseBody
    @RequestMapping("seleQueCond")
    public Map seleQueCond(@RequestParam String sno) {
        HashMap<String,Object> response = new HashMap<String,Object>();
        response.put("data",db.seleQueCond(sno));
        return response;
    }
    /*学生查询课程情况*/
    @ResponseBody
    @RequestMapping("seleGrade")
    public Map seleGrade(@RequestParam String sno) {
        HashMap<String,Object> response = new HashMap<String,Object>();
        response.put("data",db.seleGrade(sno));
        return response;
    }
    /*教师填写项目进度情况*/
    @ResponseBody
    @RequestMapping("setProgress")
    public Map setProgress(String qno,String progress) {
        HashMap<String,Object> response = new HashMap<String,Object>();
        if(db.setProgress(qno,progress)!=0) {
            response.put("msg","修改成功！");
        }else{
            response.put("msg","修改失败！");
        }
        return response;
    }
    /*教师给课程设计评分*/
    @ResponseBody
    @RequestMapping("setGrade")
    public Map setGrade(String qno,String grade) {
        HashMap<String,Object> response = new HashMap<String,Object>();
        if(db.setGrade(qno,grade)) {
            response.put("msg","成绩修改成功！");
        } else {
            response.put("msg","成绩修改失败！");
        }
        return response;
    }
    /*教师查看选题情况*/
    @ResponseBody
    @RequestMapping("seleQue")
    public Map seleQue() {
        HashMap<String,Object> response = new HashMap<String,Object>();

        if(db.seleQue().size()!=0) {
            response.put("data",db.seleQue());
            response.put("msg","查询成功");
        } else {
            response.put("msg","查询失败");
        }
        return response;
    }
    /*教师查看设计报告*/
    /*上传课程设计题目*/
    /*上传学习资料*/
    /*查看课程设计题目*/
    @ResponseBody
    @RequestMapping("seleQueName")
    public Map seleQueName() {
        HashMap<String,Object> response = new HashMap<String,Object>();
        if(db.seleQueName().size()!=0) {
            response.put("data",db.seleQueName());
            response.put("msg","获取题库成功");
        } else {
            response.put("msg","获取题库失败");
        }
        return response;
    }
    /*查看学习资料*/



}
