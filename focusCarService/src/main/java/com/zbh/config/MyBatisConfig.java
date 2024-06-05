package com.zbh.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zbh.dao")
public class MyBatisConfig {
}
