package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.OrgStatusService;
import com.demofactory.syscontrol.common.ObjResult;
import com.demofactory.syscontrol.common.Result;
import com.demofactory.syscontrol.domain.SysOrg;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author : Hanamaru
 * @description: TODO
 * @date : 2020/8/20 10:01
 */
@Slf4j
@RestController
@RequestMapping("management")
public class OrgStatusController
{

    @Reference
    private OrgStatusService orgStatusService;

    @PostMapping(value = {"deleteOrg", "enableOrg", "disableOrg"})
    public ObjResult<String> UpdateOrg(@RequestBody SysOrg sysOrg)
    {
        if (Objects.isNull(sysOrg.getId()))
        {
            log.info("result------id不能为空");
            return ObjResult.failure("id不能为空");
        }
        return orgStatusService.orgStatusUpdate(sysOrg);
    }

    //TODO 机构管理 增查
    @PostMapping("selectOrg")
    public List<SysOrg> SelectSysOrg(@RequestBody SysOrg sysOrg)
    {
        List<SysOrg> sysOrgs = null;
        sysOrgs = orgStatusService.selectSysOrg(sysOrg);
        return sysOrgs;
    }

    @PostMapping("insertOrg")
    public ObjResult<String> insertOrg(@RequestBody SysOrg sysOrg)
    {
        if (Objects.isNull(sysOrg.getDomainId()))
        {
            log.info("result------域id不能为空");
            return ObjResult.failure("域名不为空");
        }
        if (StringUtils.isBlank(sysOrg.getOrgName()))
        {
            log.info("result------机构name不能为空");
            return ObjResult.failure("机构名不能为空");
        }
        return orgStatusService.insertSysOrg(sysOrg);
    }
}
