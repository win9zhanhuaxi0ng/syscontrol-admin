package com.demofactory.syscontrol.controller.admin.index;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.common.Result;
import com.demofactory.syscontrol.common.utils.RegexUtil;
import com.demofactory.syscontrol.domain.SysUser;

import com.demofactory.syscontrol.domain.dto.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;


/**
 * @author FHX
 */
@Slf4j
@RestController
@RequestMapping("index")
public class IndexController {

    @Reference(check = false)
    private SysUserService sysUserService;

    //登录
    @PostMapping("login")
    public Result login(@RequestBody SysUser sysUser) {
        Result result = new Result();
        log.info("已进入登录验证");
        if (StringUtils.isBlank(sysUser.getAccount()) || StringUtils.isBlank(sysUser.getPassword())) {
            result.setMessage("账号密码不能为空");
            return result;
        }
        return sysUserService.login(sysUser);
    }

    //注册
    @PostMapping("register")
    public String register(@RequestBody SysUserDTO sysUserDTO) {
        if (StringUtils.isBlank(sysUserDTO.getPassword()) || StringUtils.isBlank(sysUserDTO.getAccount())
                || StringUtils.isBlank(sysUserDTO.getSecondaryPwd())) {
            return "注册信息不能为空！";
        }
        return sysUserService.register(sysUserDTO);
    }

    //忘记密码
    @PostMapping("forgetPassword")
    public Result forgetPassword(@RequestBody SysUser sysUser) {
        Result result = new Result();
        if (StringUtils.isBlank(sysUser.getAccount())||StringUtils.isBlank(sysUser.getPwdHint())) {
            result.setMessage("账号或提示语不能为空");
            return result;
        }
        return sysUserService.selectAccountAndHint(sysUser);
    }

    //修改密码
    @PostMapping("resetPassword")
    public String resetPassword(@RequestBody SysUserDTO sysUserDTO) {
        if (StringUtils.isBlank(sysUserDTO.getPassword()) || StringUtils.isBlank(sysUserDTO.getSecondaryPwd())) {
            return "输入不能为空！";
        }
        return sysUserService.updatePassword(sysUserDTO);
    }
}
