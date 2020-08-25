package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.DomainStatusService;

import com.demofactory.syscontrol.common.Result;
import com.demofactory.syscontrol.domain.SysDomain;
import com.demofactory.syscontrol.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author : Hanamaru
 * @description: TODO
 * @date : 2020/8/20 13:35
 */
@Slf4j
@RestController
@RequestMapping("management")
public class DomainStatusController
{

    @Reference
    private DomainStatusService domainStatusService;

    @PostMapping(value = {"deleteDomain", "enableDomain", "disableDomain"})
    public Result domainUpdate(@RequestBody SysDomain sysDomain)
    {
        if (Objects.isNull(sysDomain.getId()))
        {
            log.info("result------id不能为空");
            return Result.failure("id不能为空");
        }
        return domainStatusService.domainUpdate(sysDomain);
    }

    //TODO 域管理 增查
    @PostMapping("selectDomain")
    public List<SysDomain> SelectDomain(@RequestBody SysDomain sysDomain)
    {
        List<SysDomain> sysDomains = null;
        sysDomains = domainStatusService.selectSysDomain(sysDomain);
        return sysDomains;
    }

    @PostMapping("insertDomain")
    public Result insertDomain(@RequestBody SysDomain sysDomain)
    {
        if (StringUtils.isBlank(sysDomain.getName()))
        {
            log.info("result------域名不能为空");
            return Result.failure("域名不为空");
        }
        return domainStatusService.insertSysDomain(sysDomain);
    }
}
