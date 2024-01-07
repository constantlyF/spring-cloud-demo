package com.calm.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    /**
     * {@link LoadBalanced}
     * 用于将 RestTemplate bean 标记为使用 LoadBalancerClient 的注释。
     * LoadBalancerClient可以获取eureka-server注册服务的信息，不然RestTemplate仅仅是一个远程调用,无法识别eureka中的服务
     */
    @Bean
    // 自己实现需要注释掉springboot提供的
    // @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
