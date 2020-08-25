package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.SysOrgService;
import com.demofactory.syscontrol.common.Result;
import com.demofactory.syscontrol.domain.SysDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("management")
public class OrgController {
    @Reference
    private SysOrgService sysOrgService;

    @PostMapping("selectOrgListByDomain")
    public Result selectOrgListByDomain(@RequestBody SysDomain sysDomain) {
        return sysOrgService.selectOrgListByDomain(sysDomain);
    }
}
