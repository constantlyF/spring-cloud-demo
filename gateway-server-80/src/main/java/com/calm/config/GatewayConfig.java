package com.calm.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 表示当spring.cloud.gateway.enabled-config存在且为true的时候开启配置，如果matchIfMissing为true表示不存在的是也开启
@ConditionalOnProperty(value = "spring.cloud.gateway.enabled-config", havingValue = "true", matchIfMissing = false)
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("payment-service01", r -> r.path("/payment/**").uri("http://localhost:8091"))
                .route("payment-service02", r -> r.path("/payment/**").uri("http://localhost:8092"))
                .route("order-service", r -> r.path("/order/**").uri("http://localhost:8081"))
                .build();
    }
}
