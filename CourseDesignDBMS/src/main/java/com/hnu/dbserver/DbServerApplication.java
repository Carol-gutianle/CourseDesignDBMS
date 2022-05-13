package com.hnu.dbserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("com.hnu.dbserver.mapper")
@EnableConfigurationProperties
@SpringBootApplication
public class DbServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbServerApplication.class, args);
    }

}
