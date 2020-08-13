package com.demofactory.syscontrol.controller.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.demofactory.syscontrol")
public class SysControlAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysControlAdminApplication.class, args);
    }

}
