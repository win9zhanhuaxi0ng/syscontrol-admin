package com.demofactory.syscontrol.controller.admin.demo;


import com.demofactory.syscontrol.api.DemoService;
import com.demofactory.syscontrol.domain.demo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wy
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("test")
    public String test() {
        Demo demo = new Demo();
        int result = demoService.insert(demo);
        System.out.println("result" + result);
        return "result：------------" + result;
    }
}
