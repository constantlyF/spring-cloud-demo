package com.calm.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.calm.dto.CodeGenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CodeGeneratorService {
    private final DataSourceProperties dataSourceProperties;

    public void codeGenerator(String tableName) {
        codeGenerator(CodeGenDTO.of().setTableName(tableName));
    }

    /**
     * 代码生成器
     * 参考：
     */
    public void codeGenerator(CodeGenDTO codeGenDto) {
        codeGenDto.init(dataSourceProperties);
        FastAutoGenerator.create(codeGenDto.getUrl(), codeGenDto.getUsername(), codeGenDto.getPassword())
                // 关闭service接口
                .globalConfig(builder -> builder.disableServiceInterface()
                        // .fileOverride() // 覆盖生成的文件，设置到局部了
                        .outputDir(codeGenDto.getOutputDir())
                        .author(codeGenDto.getAuthor())
                        // .enableKotlin()
                        // 不用swagger过时了,使用springdoc
                        // .enableSwagger()
                        .enableSpringdoc()
                        // 使用java8下的新时间类型
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd"))
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> builder.parent(codeGenDto.getPackageName())
                        // 模块名，比如模块为demo，包名就是com.calm.demo,将模块集成到包名里面就好了没必要设置这个
                        // .moduleName("")
                        .entity("entity")
                        // 接口层被我关掉了，如果想使用标准的service serviceImpl 需要修改此处
                        // .service("service")
                        .serviceImpl("service")
                        .mapper("mapper")
                        .xml("mapper")
                        .controller("controller")
                        // 设置mapperXml生成路径
                        .pathInfo(Collections.singletonMap(OutputFile.xml, codeGenDto.getXmlDir())))
                // t1,t2 且支持正则匹配、例如 ^t_.* 所有 t_ 开头的表名
                .strategyConfig(builder -> builder.addInclude(codeGenDto.getTableName())
                        // 文件覆盖，上面的全局修改到局部了，采用更精细的控制； 开启lombok链模式； 开启lombok
                        .entityBuilder().enableFileOverride().enableChainModel().enableLombok()
                        // 字段注释； 全局主键类型
                        .enableTableFieldAnnotation().idType(IdType.AUTO)
                        // 开启生成@RestController 控制器
                        .controllerBuilder().enableFileOverride().enableRestStyle()
                        // 接口层被我关掉了，如果想使用标准的service serviceImpl 需要修改此处
                        .serviceBuilder().enableFileOverride().formatServiceImplFileName("%sService")
                        .mapperBuilder().enableFileOverride().enableBaseResultMap().enableBaseColumnList())
                // 这里看下mybatis plus 源码和 Freemarker文档，然后定义模板即可，怪麻烦的没看,用自己的不可以设置这个名字需要重新定义名字
                // 比如CusMapper.java,不然直接使用的是plus官网自己的。
                 .templateConfig(builder -> builder.mapper("/templates/mapper.java")
                         .xml("/templates/mapper.xml"))
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }


}
