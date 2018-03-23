package com.feizi.dubbo.consumer.service;

import com.feizi.dubbo.ConsumerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by feizi on 2018/3/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class ConsumerDemoServiceTest {

    @Resource
    private ConsumerDemoService consumerDemoService;

    @Test
    public void testSayHello() throws Exception {
        consumerDemoService.sayHello("feizi");
    }
}