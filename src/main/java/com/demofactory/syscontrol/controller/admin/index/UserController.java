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
    public String toLogin(String account,String password){

        SysUser sysUser = sysUserService.loginByAccountAndPassword(account,password);
        if (sysUser!= null){
            return "登录成功！";
        }
        return "登录失败！";
    }
    @GetMapping("register")
    @ResponseBody
    public String toRegister(String account,String password){
        int flag = sysUserService.registerSysUser(account,password);
        switch (flag){
            case 1:
                return "注册成功";
            case 0:
                return "账号或密码为空，请重新输入";
            case -1:
                return "账号已存在！";
            default:
                return "未知错误请联系管理员";
        }
    }
}
