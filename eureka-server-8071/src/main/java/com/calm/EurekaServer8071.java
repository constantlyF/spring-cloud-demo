package com.calm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer8071 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer8071.class,args);
    }
}