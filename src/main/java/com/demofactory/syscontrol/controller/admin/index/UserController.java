package com.demofactory.syscontrol.controller.admin.index;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.common.utils.RegexUtil;
import com.demofactory.syscontrol.domain.SysUser;

import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;



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

        if (StringUtils.isBlank(sysUser.getAccount()) || StringUtils.isBlank(sysUser.getPassword())) {
            return "账号密码不能为空";
        }
        return sysUserService.login(sysUser);

    }

    @PostMapping("register")
    public String register(@RequestBody SysUser sysUser, @RequestParam String secondaryPwd) {
        if (StringUtils.isBlank(sysUser.getPassword()) || StringUtils.isBlank(sysUser.getAccount()) || StringUtils.isBlank(secondaryPwd)) {
            return "注册信息不能为空！";
        }
        return sysUserService.register(sysUser, secondaryPwd);
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
    public String updatePassword(@RequestBody SysUser sysUser, @RequestParam String secondaryPwd) {
        if (StringUtils.isBlank(sysUser.getPassword()) || StringUtils.isBlank(secondaryPwd)) {
            return "输入不能为空！";
        }
        //密码正则表达式验证
        if (!RegexUtil.checkRegex(RegexUtil.REGEX_PASSWORD, sysUser.getPassword())) {
            return "重置密码格式不正确！";
        }
        return sysUserService.updatePassword(sysUser, secondaryPwd);
    }
}
