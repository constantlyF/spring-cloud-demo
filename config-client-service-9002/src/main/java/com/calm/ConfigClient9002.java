package com.calm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClient9002 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient9002.class, args);
    }
}