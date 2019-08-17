package com.jtfu;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.jtfu.demo.mapper")
public class Demo2 {
    public static void main(String[] args) {
        SpringApplication.run(Demo2.class);
    }

    @Bean
    public MybatisSqlSessionFactoryBean factoryBean(DataSource dataSource){
        MybatisSqlSessionFactoryBean factoryBean=new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration=new MybatisConfiguration();
        /*configuration.setDefaultEnumTypeHandler(EnumTypeHandler.class);*/
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(configuration);
        return factoryBean;
    }
}
