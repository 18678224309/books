package com.jtfu.gen.controller;


import com.jtfu.gen.service.GenService;
import com.jtfu.gen.util.CodeGenerator;
import com.jtfu.gen.util.LayUiObj;
import com.jtfu.gen.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenController {

    @Autowired
    GenService genService;

    @Autowired
    CodeGenerator generator;


    @GetMapping("/getTables")//http://127.0.0.1:8080/demo/table/user/?page=1&limit=10 404
    public LayUiObj getTables(@RequestParam("page")int page,@RequestParam("limit")int limit){
        return genService.getTables(page,limit);
    }

    @PostMapping("/genSource")
    public R genSource(@RequestParam("tableNames")String tableNames){
        generator.Gen(tableNames);
        return R.success();
    }
}
