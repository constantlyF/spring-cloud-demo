package com.calm.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
@Tag(name = "ConfigClientController-config")
@Slf4j
public class ConfigClientController {
    @Value("${config}")
    private String config;

    @Value("${server.port}")
    private String port;

    @Value("${name}")
    private String name;

    @GetMapping("/config")
    public String config() {
        return config + port +name;
    }
}
