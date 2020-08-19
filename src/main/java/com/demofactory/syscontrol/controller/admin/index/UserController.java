package com.demofactory.syscontrol.controller.admin.index;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.controller.admin.utils.RegexUtil;
import com.demofactory.syscontrol.domain.SysUser;

import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("index")
public class UserController {
    //手机号正则表达式
    private static final String REGEX_MOBILE = "^1[3|4|5|7|8][0-9]{9}$";
    //邮箱正则表达式
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //密码正则表达式
    private static final String REGEX_PASSWORD = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,}$";

    @Reference(check = false)
    private SysUserService sysUserService;

    @PostMapping("login")
    public String login(@RequestBody SysUser sysUser) {

        if (sysUser.getAccount().equals("") || sysUser.getPassword().equals("")) {
            return "账号密码不能为空";
        }
        SysUser sysUser1 = sysUserService.login(sysUser);
        if (sysUser1 == null) {
            return "账号密码错误";
        }
        if (sysUser1.getStatus() == 1) {
            LocalDateTime localDateTime = sysUser1.getLastLoginTime();
            sysUserService.updateLastLoginTime(sysUser1);
            if (localDateTime == null) {
                return "欢迎您第一次登录！";
            }
            return "登录成功！上次登录时间为：" + localDateTime;
        }
        return "账户已删除或停用！";
    }

    @PostMapping("register")
    public String register(@RequestBody SysUser sysUser, @RequestParam String secondaryPwd) {
        if (sysUser.getPassword().equals("") || sysUser.getAccount().equals("") || secondaryPwd.equals("")) {
            return "注册信息不能为空！";
        }
        //含有正则表达式验证方法的自定义类
        RegexUtil regexUtil = new RegexUtil();
        //手机正则表达式验证
        boolean flagMobile = regexUtil.checkRegex(REGEX_MOBILE,sysUser.getAccount());
        //邮箱正则表达式验证
        boolean flagEmail = regexUtil.checkRegex(REGEX_EMAIL,sysUser.getAccount());
        //密码正则表达式验证
        boolean flagPassword = regexUtil.checkRegex(REGEX_PASSWORD,sysUser.getPassword());

        if (flagEmail) {
            sysUser.setUserEmail(sysUser.getAccount());
        } else if (flagMobile) {
            sysUser.setUserPhone(sysUser.getAccount());
        } else {
            return "账号格式不符合！";
        }
        if (!flagPassword){
            return "密码格式不符合！";
        }
        int flag = sysUserService.register(sysUser, secondaryPwd);
        switch (flag) {
            case 1:
                return "注册成功";
            case -1:
                return "账号已存在！";
            case -2:
                return "两次密码输入不一致！";
            default:
                return "未知错误请联系管理员";
        }
    }

    @PostMapping("forgetPassword")
    public String forgetPassword(SysUser sysUser) {
        if (StringUtils.isBlank(sysUser.getAccount())) {
            return "账号不能为空";
        }

        if (StringUtils.isBlank(sysUser.getPwdHint())) {
            return "提示语不能为空";
        }
        return sysUserService.SelectAccountAndHint(sysUser);
    }
}
