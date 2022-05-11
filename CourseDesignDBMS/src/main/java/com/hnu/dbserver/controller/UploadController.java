package com.hnu.dbserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class UploadController {
    @GetMapping("/")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String create(@RequestPart MultipartFile file) throws IOException {
        String path = "C://gtl/";
        String fileName = file.getOriginalFilename();
        String filePath = path + fileName;

        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        return "Upload file success : " + dest.getAbsolutePath();
    }
}
