package com.demofactory.syscontrol.controller.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.demofactory.syscontrol")
@MapperScan({"com.demofactory.syscontrol.dao","com.baomidou.mybatisplus.samples.quickstart.mapper"})
public class SysControlAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysControlAdminApplication.class, args);
    }

}
