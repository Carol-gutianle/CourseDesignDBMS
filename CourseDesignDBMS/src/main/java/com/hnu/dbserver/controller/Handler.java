package com.hnu.dbserver.controller;

import com.hnu.dbserver.mapper.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class Handler {
    @Autowired
    DataBase db;
    @ResponseBody
    @RequestMapping("stuLogin")
    public Map stuLogin(@RequestParam String sno, String spwd) {
        HashMap<String,Object> response = new HashMap<String,Object>();
        if(db.stuLogin(sno,spwd).size()!=0){
            response.put("code",200);
            response.put("msg","登录成功");
            response.put("type","学生");
            return response;
        }
        else {
            response.put("code",404);
            response.put("msg","登录失败");
            response.put("type","学生");
            return response;
        }
    }
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

}
