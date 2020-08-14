package com.demofactory.syscontrol.controller.admin.index;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.domain.SysUser;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("index")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("login")
    @ResponseBody
    public String toLogin(String account){

        SysUser sysUser = sysUserService.loginByAccount(account);

        return sysUser.getPassword();
    }
}
