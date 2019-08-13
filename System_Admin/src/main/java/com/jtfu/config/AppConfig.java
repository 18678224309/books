package com.jtfu.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.handlers.EnumAnnotationTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.EnumTypeHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.jtfu.mapper")
public class AppConfig {


    @Bean
    public MybatisSqlSessionFactoryBean sessionFactoryBean(DataSource dataSource){
        MybatisSqlSessionFactoryBean factoryBean=new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration=new MybatisConfiguration();
        configuration.setDefaultEnumTypeHandler(EnumTypeHandler.class);
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(configuration);
        return factoryBean;
    }

}
