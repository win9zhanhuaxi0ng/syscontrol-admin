package com.demofactory.syscontrol.controller.admin.index;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.common.utils.RegexUtil;
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

    @Reference(check = false)
    private SysUserService sysUserService;

    //登录
    @PostMapping("login")
    public String login(@RequestBody SysUser sysUser) {

        if ("".equals(sysUser.getAccount()) || "".equals(sysUser.getPassword())) {
            return "账号密码不能为空";
        }
        switch (sysUserService.login(sysUser)) {
            case 1:
                return "登录成功";
            case 0:
                return "账号不存在";
            case -1:
                return "账户已停用";
            case -2:
                return "账号密码不正确";
            default:
                return "请联系管理员";
        }

    }

    @PostMapping("register")
    public String register(@RequestBody SysUser sysUser, @RequestParam String secondaryPwd) {
        if ("".equals(sysUser.getPassword()) || "".equals(sysUser.getAccount()) || "".equals(secondaryPwd)) {
            return "注册信息不能为空！";
        }
        switch (sysUserService.register(sysUser, secondaryPwd)) {
            case 1:
                return "注册成功";
            case -1:
                return "账号格式不正确！请填写正确手机号或邮箱";
            case -2:
                return "密码格式不正确！请输入包含大小写字母+数字的6位以上密码";
            case -3:
                return "两次密码输入不一致";
            case -4:
                return "账号已存在";
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
        return sysUserService.selectAccountAndHint(sysUser);
    }

    @PostMapping("updatePassword")
    public String updatePassword(@RequestBody SysUser sysUser, @RequestParam String secondaryPwd) {
        if ("".equals(sysUser.getPassword()) || "".equals(secondaryPwd)) {
            return "输入不能为空！";
        }
        //密码正则表达式验证
        if (!RegexUtil.checkRegex(RegexUtil.REGEX_PASSWORD, sysUser.getPassword())) {
            return "重置密码格式不正确！";
        }
        return sysUserService.updatePassword(sysUser, secondaryPwd);
    }
}
