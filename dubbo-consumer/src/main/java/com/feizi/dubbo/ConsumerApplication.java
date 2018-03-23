package com.feizi.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 * 消费端启动服务
 */
@SpringBootApplication
@ComponentScan(value = {"com.feizi.dubbo"})
public class ConsumerApplication {
    public static void main( String[] args ){
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
