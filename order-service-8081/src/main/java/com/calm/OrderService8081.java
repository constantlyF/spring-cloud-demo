package com.calm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.calm.mapper")
public class OrderService8081 {
    public static void main(String[] args) {
        SpringApplication.run(OrderService8081.class, args);
    }
}