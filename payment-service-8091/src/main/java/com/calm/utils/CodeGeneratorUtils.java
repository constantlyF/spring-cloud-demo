package com.calm.utils;


import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

/**
 * 参考： https://baomidou.com/pages/981406/#%E5%9F%BA%E7%A1%80%E9%85%8D%E7%BD%AE
 */
@Service
@RequiredArgsConstructor
public class CodeGeneratorUtils {

    private final DataSourceProperties dataSourceProperties;

    private DataSourceConfig dataSourceConfig() {
        new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/mybatis-plus", "root", "123456")
                .build();
    }

}
