package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.DomainStatusService;

import com.demofactory.syscontrol.domain.SysDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Hanamaru
 * @description: TODO
 * @date : 2020/8/20 13:35
 */
@Slf4j
@RestController
@RequestMapping("management")
public class DomainStatusController {

    @Reference
    private DomainStatusService domainStatusService;

    @PostMapping(value = {"deleteDomain", "enableDomain", "disableDomain"})
    public String domainUpdate(SysDomain sysDomain) {
        return domainStatusService.domainUpdate(sysDomain);
    }
}
