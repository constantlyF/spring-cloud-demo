package com.calm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ribbon.rule.MyRule;

@SpringBootApplication
@MapperScan("com.calm.mapper")
@EnableEurekaClient
// 服务发现
@EnableDiscoveryClient
@EnableFeignClients
// 这里仅仅针对PAYMENT-SERVICE服务应用我们的规则
@RibbonClient(name = "PAYMENT-SERVICE",configuration= MyRule.class)
public class OrderService8081 {
    public static void main(String[] args) {
        SpringApplication.run(OrderService8081.class, args);
    }
}