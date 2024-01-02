package com.calm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.calm.mapper")
public class CommonMain {
    public static void main(String[] args) {
        SpringApplication.run(CommonMain.class, args);
    }
}