package com.jtfu.demo.controller;

import com.jtfu.annotation.JtfuTransacation;
import com.jtfu.demo.entity.Demo;
import com.jtfu.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo2Controller {

    @Autowired
    DemoMapper demoMapper;

    @PostMapping("insertDemo2")
    @Transactional
    @JtfuTransacation(isEnd = true)
    public String insertDemo1() throws Exception {
        Demo demo=new Demo();
        demo.setValue("--------------------2");
        demoMapper.insert(demo);
        return "success";
    }
}
