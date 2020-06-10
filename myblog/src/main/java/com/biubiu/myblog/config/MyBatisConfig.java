package com.biubiu.myblog.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;

/**
 * MyBatisConfig
 *
 * @author biubiu
 */
@Configuration
@MapperScan("com.biubiu.myblog.dao")
public class MyBatisConfig {

    /**
     * 分页插件 PageHelper
     */
    @Bean
    public PageHelper getPageHelperConfig() {
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("helperDialect","mysql");
        properties.setProperty("reasonable","true");
        properties.setProperty("supportMethodsArguments","true");
        properties.setProperty("params","count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
