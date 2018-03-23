package com.feizi.dubbo.consumer.controller;

import com.feizi.dubbo.api.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by feizi on 2018/3/8.
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping("/info/{name}")
    public String demo(@PathVariable("name") String name){
        return demoService.sayHello(name);
    }
}
