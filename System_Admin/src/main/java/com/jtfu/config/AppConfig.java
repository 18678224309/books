package com.jtfu.config;

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
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }

}
