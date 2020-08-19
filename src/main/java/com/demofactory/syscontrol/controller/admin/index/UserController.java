package com.demofactory.syscontrol.controller.admin.index;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.controller.admin.utils.RegexUtil;
import com.demofactory.syscontrol.domain.SysUser;

import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * @author FHX
 */
@RestController
@RequestMapping("index")
public class UserController {
    //手机号正则表达式
    /**
     * REGEX_MOBILE 手机号正则表达式
     * REGEX_EMAIL  邮箱正则表达式
     * REGEX_PASSWORD   密码正则表达式
     */
    private static final String REGEX_MOBILE = "^1[3|4|5|7|8][0-9]{9}$";
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String REGEX_PASSWORD = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,}$";

    @Reference(check = false)
    private SysUserService sysUserService;
    //登录
    @PostMapping("login")
    public String login(@RequestBody SysUser sysUser) {

        if ("".equals(sysUser.getAccount()) || "".equals(sysUser.getPassword())) {
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
        if ("".equals(sysUser.getPassword()) || "".equals(sysUser.getAccount()) || "".equals(secondaryPwd)) {
            return "注册信息不能为空！";
        }
        //自定义RegexUtil类，实现正则表达式验证
        //正则表达式验证
        if (RegexUtil.checkRegex(REGEX_EMAIL,sysUser.getAccount())) {
            sysUser.setUserEmail(sysUser.getAccount());
        } else if (RegexUtil.checkRegex(REGEX_MOBILE,sysUser.getAccount())) {
            sysUser.setUserPhone(sysUser.getAccount());
        } else {
            return "账号格式不符合！";
        }
        if (!RegexUtil.checkRegex(REGEX_PASSWORD,sysUser.getPassword())){
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
    public String forgetPassword(@RequestBody SysUser sysUser) {
        if (StringUtils.isBlank(sysUser.getAccount())) {
            return "账号不能为空";
        }

        if (StringUtils.isBlank(sysUser.getPwdHint())) {
            return "提示语不能为空";
        }
        return sysUserService.selectAccountAndHint(sysUser);
    }

    @PostMapping("updatePassword")
    public String updatePassword(@RequestBody SysUser sysUser,@RequestParam String secondaryPwd){
        if ("".equals(sysUser.getPassword())|| "".equals(secondaryPwd)){
            return "输入不能为空！";
        }
        //密码正则表达式验证
        if(!RegexUtil.checkRegex(REGEX_PASSWORD,sysUser.getPassword())){
            return "重置密码格式不正确！";
        }
        return sysUserService.updatePassword(sysUser,secondaryPwd);
    }
}
