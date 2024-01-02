package com.calm.controller;

import com.calm.common.model.ResultData;
import com.calm.dto.CodeGenDTO;
import com.calm.service.CodeGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/code-generator")
@Tag(name = "CodeGenerator-代码生成器")
@RequiredArgsConstructor
public class CodeGeneratorController {
    private final CodeGeneratorService codeGeneratorService;

    @Operation(summary = "mybatis plus 代码生成器")
    @GetMapping("/{tableName}")
    public ResultData<Void> codeGenerator(
            @Parameter(name = "tableName", description = "表名称, table01,table02", required = true, in = ParameterIn.PATH)
            @PathVariable(value = "tableName") String tableName
    ) {
        codeGeneratorService.codeGenerator(tableName);
        return ResultData.success();
    }

    @Operation(summary = "mybatis plus 代码生成器")
    @PostMapping("/")
    public ResultData<Void> codeGenerator(@RequestBody @Valid CodeGenDTO codeGenDTO) {
        codeGeneratorService.codeGenerator(codeGenDTO);
        return ResultData.success();
    }
}
