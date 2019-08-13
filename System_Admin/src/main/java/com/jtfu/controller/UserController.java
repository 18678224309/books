package com.jtfu.controller;


import com.jtfu.entity.User;
import com.jtfu.myenum.RoleEnum;
import com.jtfu.service.IUserService;
import com.jtfu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jtfu
 * @since 2019-08-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/getUser")
    public R getUser(){
        RoleEnum.class.getEnumConstants();
        User user=userService.getById(1);
        return R.success().set("user",user);
    }
}
