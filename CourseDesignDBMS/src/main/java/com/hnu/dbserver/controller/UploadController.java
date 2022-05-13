package com.hnu.dbserver.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Component
@RestController
public class UploadController {
    /**
     * 本地保存路径
     */
//    @Value("${file.path}")
    String dirPath = "T:\\gtl\\";

    @RequestMapping(value = "upload")
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


}
