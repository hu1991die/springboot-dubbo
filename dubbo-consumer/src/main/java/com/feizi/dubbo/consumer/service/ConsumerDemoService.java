package com.feizi.dubbo.consumer.service;

import com.feizi.dubbo.api.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by feizi on 2018/3/7.
 */
@Service("consumerDemoService")
public class ConsumerDemoService {

    @Resource
    private DemoService demoService;

    public void sayHello(String name){
        String hello = demoService.sayHello(name); // 执行消费远程方法
        System.out.println(hello); // 显示调用结果
    }
}
