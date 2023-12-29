package com.calm.controller;

import com.calm.common.model.ResultData;
import com.calm.service.CodeGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("code-generator")
@Tag(name = "CodeGenerator-代码生成器")
@RequiredArgsConstructor
public class CodeGenerator {
    private final CodeGeneratorService codeGeneratorService;

    @Operation(summary = "mybatis plus 代码生成器")
    @PostMapping("/")
    public ResultData<Void> codeGenerator(
            @Parameter(name = "tableName", description = "表名称, table01,table02", required = true, in = ParameterIn.QUERY)
            @RequestParam(value = "tableName") String tableName,
            @Parameter(name = "tableNamePrefixList", description = "前缀过滤", in = ParameterIn.QUERY)
            @RequestParam(value = "tableNamePrefixList", required = false) List<String> tableNamePrefixList
    ) {
        codeGeneratorService.codeGenerator(tableName, tableNamePrefixList);
        return ResultData.success();
    }
}
