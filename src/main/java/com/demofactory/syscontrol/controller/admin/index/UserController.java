package com.demofactory.syscontrol.controller.admin.index;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.domain.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

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
            LocalDateTime localDateTime = sysUserService.findLastLoginTimeByAccount(account);
            sysUserService.updateLastLoginTime(LocalDateTime.now(),account);
            if (localDateTime == null){
                return "欢迎您第一次登录！";
            }
            return "登录成功！上次登录时间为："+localDateTime;
        }
        return "登录失败！";
    }
    @GetMapping("register")
    @ResponseBody
    public String toRegister(String account,String password,String secondaryPwd,String pwdHint){
        int flag = sysUserService.registerSysUser(account,password,secondaryPwd,pwdHint);
        switch (flag){
            case 1:
                return "注册成功";
            case 0:
                return "账号或密码为空，请重新输入";
            case -1:
                return "账号已存在！";
            case -2:
                return "两次密码输入不一致！";
            default:
                return "未知错误请联系管理员";
        }
    }
}
