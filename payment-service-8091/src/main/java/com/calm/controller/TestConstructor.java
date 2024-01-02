package com.calm.controller;

import com.calm.common.model.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "TestConstructor-测试项目是否启动成功")
@RequiredArgsConstructor
public class TestConstructor {

    @Operation(summary = "测试项目是否成功")
    @GetMapping("")
    public ResultData<Void> success() {
        return ResultData.success();
    }
}
