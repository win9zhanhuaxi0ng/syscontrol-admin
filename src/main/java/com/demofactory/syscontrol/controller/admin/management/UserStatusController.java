package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.UserStatusService;
import com.demofactory.syscontrol.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author : Hanamaru
 * @description: TODO
 * @date : 2020/8/19 17:16
 */
@Slf4j
@RestController
@RequestMapping("management")
public class UserStatusController {

    @Reference(check = false)
    private UserStatusService userStatusService;

    @PostMapping(value = {"deleteUser","enableUser","disableUser"})
    public String UpdateUser(SysUser sysUser)
    {
        if(Objects.isNull(sysUser.getStatus()))
        {
            log.info("result------status不能为空");
            return "status不能为空";
        }
        return userStatusService.userStatusUpdate(sysUser);
    }
}
