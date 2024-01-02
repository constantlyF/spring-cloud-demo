package com.calm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.validation.constraints.NotBlank;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class CodeGenDTO {
    @Schema(description = "t1,t2 且支持正则匹配、例如 ^t_.* 所有 t_ 开头的表名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "tableName 不允许为空")
    private String tableName;
    @Schema(description = "数据库url", defaultValue = "默认读取yaml")
    private String url;
    @Schema(description = "数据库userName", defaultValue = "默认读取yaml")
    private String username;
    @Schema(description = "数据库password", defaultValue = "默认读取yaml")
    private String password;
    @Schema(description = "java生成文件的输出目录", defaultValue = "window->D:/; centos->/tmp/")
    private String outputDir;
    @Schema(description = "mapper xml路径", defaultValue = "window->D:/+outputDir+/resource; centos->/tmp/+outputDir+/resource")
    private String xmlDir;
    @Schema(description = "包名", defaultValue = "com.calm")
    private String packageName;
    @Schema(description = "作者", defaultValue = "calm")
    private String author;

    public void init(DataSourceProperties dataSourceProperties) {
        this.url = StringUtils.defaultIfBlank(url, dataSourceProperties.getUrl());
        this.username = StringUtils.defaultIfBlank(username, dataSourceProperties.getUsername());
        this.password = StringUtils.defaultIfBlank(password, dataSourceProperties.getPassword());
        this.author = StringUtils.defaultIfBlank(author, "calm");
        this.packageName = StringUtils.defaultIfBlank(packageName, "com.calm");
        String path = System.getProperty("os.name").toLowerCase().contains("windows") ? "D:/" : "/tmp/";
        this.outputDir = StringUtils.defaultIfBlank(outputDir, path);
        this.xmlDir = StringUtils.defaultIfBlank(xmlDir, path + this.packageName.replace(".", "/") + "/resource");
    }
}
