package com.jtfu.demo.controller;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.jtfu.annotation.JtfuTransacation;
import com.jtfu.demo.entity.Demo;
import com.jtfu.demo.mapper.DemoMapper;
import com.jtfu.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo1Controller {

    @Autowired
    DemoMapper demoMapper;


    @GetMapping("insertDemo1")
    @Transactional
    @JtfuTransacation(isStart = true)
    public String insertDemo1() throws Exception {
        Demo demo=new Demo();
        demo.setValue("--------------------1");
        demoMapper.insert(demo);
        //restTemplate.getForObject("http://127.0.0.1:8001/insertDemo2",String.class);
        HttpUtil.post("http://127.0.0.1:8001/insertDemo2");
        return "success";
    }
}
