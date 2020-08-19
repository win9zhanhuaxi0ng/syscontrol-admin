package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.AssignUserService;
import com.demofactory.syscontrol.domain.SysUser;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author : Hanamaru
 * @description: 分配用户Controller
 * @date : 2020/8/19 13:24
 */
@RestController
@RequestMapping("management")
public class AssignUserController {

    @Reference(check = false)
    private AssignUserService assignUserService;

    @PostMapping("selectAssignUser")
    public List<SysUser> SelectToBeAssignedUser(SysUser sysUser) {
        List<SysUser> sysUsers = null;
        sysUsers = assignUserService.selectAssignUser(sysUser);
        return sysUsers;
    }

    @PostMapping(value = {"updateAssignUser","assignUnassignedUser"})
    public String updateAssignUser(SysUser sysUser) {
        if (StringUtils.isBlank(sysUser.getAccount())) {
            System.out.println("result------用户名为空");
            return "用户名不为空";
        }
        if (Objects.isNull(sysUser.getDomainId())) {
            System.out.println("result------域为空");
            return "域不为空";
        }
        if (Objects.isNull(sysUser.getOrgId())) {
            System.out.println("result------机构为空");
            return "机构不为空";
        }
        return assignUserService.updateAssignUser(sysUser);
    }
}
