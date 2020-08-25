package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.SysDomainService;
import com.demofactory.syscontrol.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("management")
public class DomainController {
    @Reference(check = false)
    private SysDomainService sysDomainService;

    @GetMapping("selectDomainList")
    public Result selectDomainList(){
        return sysDomainService.selectDomainList();
    }
}
