package com.demofactory.syscontrol.controller.admin.management;

import com.demofactory.syscontrol.api.SysUserService;
import com.demofactory.syscontrol.api.UserStatusService;
import com.demofactory.syscontrol.domain.Books;
import com.demofactory.syscontrol.domain.SysUser;
import com.demofactory.syscontrol.domain.UserBook;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("management")
public class UserController {

    @Reference(check = false)
    private SysUserService sysUserService;

    /**
     * 显示用户域名和组织名
     *
     * @param sysUser
     * @return
     */
    @PostMapping("findDomainAndOrg")
    public String findDomainAndOrg(@RequestBody SysUser sysUser) {
        return sysUserService.selectSysDomainAndSysOrg(sysUser);
    }

    /**
     * 加入域和组织
     *
     * @param sysUser
     * @return
     */
    @PostMapping("joinDomianAndOrg")
    public String joinDomianAndOrg(@RequestBody SysUser sysUser) {
        return sysUserService.insertSysDomainAndSysOrg(sysUser);
    }

    /**
     * 列出用户所在域下的所有书
     *
     * @param sysUser
     * @return
     */
    @PostMapping("listBooksByDomain")
    public List<Books> listBooksByDomain(@RequestBody SysUser sysUser) {
        return sysUserService.selectBooksByUserDomainId(sysUser);
    }

    /**
     * 用户选择书进行添加
     *
     * @param userBook
     * @return
     */
    @PostMapping("insertBooksToUser")
    public String insertBooksToUser(@RequestBody UserBook userBook) {
        return sysUserService.insertBooksToUser(userBook);
    }

    /**
     * 列出用户下的书列表
     *
     * @param sysUser
     * @return
     */
    @PostMapping("listBooksByUser")
    public List<Books> listBooksByUser(@RequestBody SysUser sysUser) {
        return sysUserService.selectBooksByUserId(sysUser);
    }

    /**
     * 用户删除书功能
     *
     * @param userBook
     * @return
     */
    @PostMapping("deleteBooksByUser")
    public String deleteBooksByUser(@RequestBody UserBook userBook) {
        return sysUserService.deleteBooksByUser(userBook);
    }

}
