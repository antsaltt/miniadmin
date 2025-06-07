package com.example.tianmuadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.tianmuadmin.mapper")
public class TianmuAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TianmuAdminApplication.class, args);
    }

}
