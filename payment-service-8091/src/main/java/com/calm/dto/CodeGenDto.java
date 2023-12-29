package com.calm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class CodeGenDto {

    @Schema(description = "tableName 需要生成的表", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tableName;
    @Schema(description = "", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tableName;

    @Schema(description = "模块名称，没啥用", defaultValue = "springboot")
    private String moduleName;


    @Schema(description = "数据库url", defaultValue = "默认读取yaml")
    private String url;
    @Schema(description = "数据库userName", defaultValue = "默认读取yaml")
    private String userName;
    @Schema(description = "数据库password", defaultValue = "默认读取yaml")
    private String password;

    @Schema(description = "生成文件的输出目录", defaultValue = "window->D://; centos->/tmp")
    private String outputDir;
    @Schema(description = "作者", defaultValue = "calm")
    private String author;

    @Schema(description = "包名", defaultValue = "com.calm")
    private String packageName;
    @Schema(description = "模块名称,比如是data-service，模块可以起名data,和包名组合为com.calm.data",
            defaultValue = "")
    private String moduleName;
    @Schema(description = "指定实体包名", defaultValue = "entity")
    private String entity;
    @Schema(description = "mapper xml路径", defaultValue = "window->D://resource; centos->/tmp/resource")
    private String mapperXml;








}
