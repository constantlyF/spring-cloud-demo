package com.calm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.calm.mapper")
@EnableEurekaClient
public class PaymentService8092 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentService8092.class, args);
    }
}