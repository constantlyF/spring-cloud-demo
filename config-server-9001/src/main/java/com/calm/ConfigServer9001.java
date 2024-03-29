package com.calm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer9001 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer9001.class, args);
    }
}