package com.feizi.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *  启动提供方服务
 */
@SpringBootApplication
@ComponentScan(value = {"com.feizi.dubbo"})
public class ProviderApplication {
    public static void main( String[] args ){
        SpringApplication.run(ProviderApplication.class, args);
    }
}
