package com.demofactory.syscontrol.controller.admin.index;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.common.utils.RegexUtil;
import com.demofactory.syscontrol.domain.SysUser;

import com.demofactory.syscontrol.domain.dto.SysUserDTO;
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
    public String register(@RequestBody SysUserDTO sysUserDTO) {
        if (StringUtils.isBlank(sysUserDTO.getPassword()) || StringUtils.isBlank(sysUserDTO.getAccount())
                || StringUtils.isBlank(sysUserDTO.getSecondaryPwd())) {
            return "注册信息不能为空！";
        }
        return sysUserService.register(sysUserDTO);
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
    public String updatePassword(@RequestBody SysUserDTO sysUserDTO) {
        if (StringUtils.isBlank(sysUserDTO.getPassword()) || StringUtils.isBlank(sysUserDTO.getSecondaryPwd())) {
            return "输入不能为空！";
        }
        return sysUserService.updatePassword(sysUserDTO);
    }
}
