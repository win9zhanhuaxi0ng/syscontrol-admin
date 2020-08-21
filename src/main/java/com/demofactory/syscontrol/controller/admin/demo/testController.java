package com.demofactory.syscontrol.controller.admin.demo;

import com.demofactory.syscontrol.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/order")
public class testController {


    @RequestMapping("/login")
    private Result login(){
        Result result=new Result();
        result.setMessage("登录成功");
        result.setSuccess(true);
        Map<String,String> map=new HashMap<>();
        map.put("content","i am content");
        return Result.ok(map);
    }
}
