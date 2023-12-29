package com.calm.service;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.calm.dto.CodeGenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Types;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeGeneratorService {
    private final DataSourceProperties dataSourceProperties;
    private final String OUTPUT_DIR = "D://";
    private final String
    /**
     * 这里知识生成个文件xml什么都没有，根本不能用
     * @param tableName
     * @param tableNamePrefixList
     */
    public void codeGenerator(CodeGenDto codeGenDto) {
        List<String> prefixList = CollectionUtils.isEmpty(tableNamePrefixList) ? Collections.emptyList() : tableNamePrefixList;
        FastAutoGenerator.create(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword())
                .globalConfig(builder -> {
                    builder.author("calm") // 设置作者
                            .disableServiceInterface() // 关闭service接口
                            // .enableSwagger()  // 开启 swagger 模式
                            .enableSpringdoc() // 开启 Springdoc 模式
                            .outputDir(path); // 设置输出目录 默认D盘或者/tmp
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.calm") // 设置父包名
                            .moduleName("spring-cloud") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "resources")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix(prefixList); // 设置过滤表前缀
                })
                .templateConfig()
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


    private DataSourceConfig dataSourceConfig(CodeGenDto codeGenDto) {
        return new DataSourceConfig.Builder(codeGenDto.getUrl(),
                codeGenDto.getUserName(),
                codeGenDto.getPassword())
                .schema("mybatis-plus")
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();
    }


    private GlobalConfig globalConfig(CodeGenDto codeGenDto) {
        return new GlobalConfig.Builder()
                // .fileOverride() // 版本已经最高了还是没有这个api，先不管了，这个是文件覆盖的。
                .outputDir(codeGenDto.getOutputDir())
                .author(codeGenDto.getAuthor())
                // .enableKotlin()
                // .enableSwagger() // 不用swagger过时了,使用springdoc
                .enableSpringdoc()
                .dateType(DateType.TIME_PACK) // 使用java8下的新时间类型
                .commentDate("yyyy-MM-dd")
                .build();
    }


    private PackageConfig packageConfig(CodeGenDto codeGenDto) {
        return new PackageConfig.Builder()
                .parent(codeGenDto.getPackageName())
                .moduleName(codeGenDto.getModuleName())
                .entity(codeGenDto.getEntity())
                .service("service")
                .mapper("mapper")
                .xml("mapper")
                .controller("controller")
                .pathInfo(Collections.singletonMap(OutputFile.xml, codeGenDto.getMapperXml()))
                .build();
    }

    private TemplateConfig templateConfig(CodeGenDto codeGenDto) {
        return new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .xml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();
    }


    private StrategyConfig strategyConfig(CodeGenDto codeGenDto) {
        return new StrategyConfig.Builder()
                .enableCapitalMode()
                .enableSkipView()
                .disableSqlFilter()
                .likeTable(new LikeTable("USER"))
                .addInclude("t_simple")
                .addTablePrefix("t_", "c_")
                .addFieldSuffix("_flag")
                .build();
    }
}
