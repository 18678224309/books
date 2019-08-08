package com.jtfu.zuul.controller;

import com.jtfu.zuul.util.R;
import com.jtfu.zuul.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    @Autowired
    RedisUtil redisUtil;
    @GetMapping("/testRedis")
    public R testRedis(){
        redisUtil.set("test","test1");
        System.out.println(redisUtil.get("test"));
        return R.success();
    }
}
