package com.demofactory.syscontrol.controller.admin.demo;

import com.demofactory.syscontrol.common.Result;
import com.demofactory.syscontrol.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("user")
public class testController {


    /**
     * 前端提交后端
     * @param users
     * @return
     */
    @RequestMapping(value="login",method= RequestMethod.POST)
    private Map login(@RequestBody User[] users){
            for(User u:users){
                System.out.println(u.getName()+"  "+u.getAge()+"  "+u.getBirth());
            }
        Map map = new HashMap();
        map.put("success","123");
        return map;
    }
}
