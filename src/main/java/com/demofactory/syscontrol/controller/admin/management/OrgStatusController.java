package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.OrgStatusService;
import com.demofactory.syscontrol.domain.SysOrg;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Hanamaru
 * @description: TODO
 * @date : 2020/8/20 10:01
 */
@Slf4j
@RestController
@RequestMapping("management")
public class OrgStatusController {

    @Reference
    private OrgStatusService orgStatusService;

    @PostMapping(value = {"deleteOrg", "enableOrg", "disableOrg"})
    public String UpdateOrg(SysOrg sysOrg) {
        return orgStatusService.orgStatusUpdate(sysOrg);

    }
}
