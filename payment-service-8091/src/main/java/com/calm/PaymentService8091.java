package com.calm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.calm.mapper")
public class PaymentService8091 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentService8091.class, args);
    }
}