package com.hnu.dbserver.controller;

import com.hnu.dbserver.mapper.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
@CrossOrigin
@RestController
public class UploadController {
    @Autowired
    DataBase db;
    /**
     * 本地保存路径
     */
    String dirPath = "T:\\gtl\\";

    public String upload(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        //文件后缀
        String prefix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID() + prefix;
        System.out.println(fileName);
        Files.copy(inputStream,new File(dirPath + fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        //拼接上传文件路径
        String returnPath = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(fileName)
                .toUriString();
        return returnPath;
    }
    /*学生文件上传*/
    @PostMapping(value = "/stuUpload")
    public Map stuUpload(MultipartFile file, String th) throws IOException {
        HashMap<String,Object> response = new HashMap<String,Object>();
        String qno=th; //题号获取
        String returnPath = upload(file);
        if(db.stuSetfile(qno,returnPath)) {

            response.put("code", 200);
            response.put("msg", "上传报告成功！");
        }
        else{
            response.put("code", 404);
            response.put("msg", "上传报告失败！");
        }
        return response;

    }
    @PostMapping(value = "/teaUpload")
    public Map teacher_upload(MultipartFile file, String tno, String tname) throws IOException {
        HashMap<String,Object> response = new HashMap<String,Object>();
        String qno=tno; //题号获取
        String qname=tname;
        String returnPath = upload(file);
        if(db.addRecord1(qno,qname,returnPath)) {

            response.put("code", 200);
            response.put("msg", "上传成功！");
        }
        else{
            response.put("code", 404);
            response.put("msg", "上传失败！");
        }
        return response;

    }
}
