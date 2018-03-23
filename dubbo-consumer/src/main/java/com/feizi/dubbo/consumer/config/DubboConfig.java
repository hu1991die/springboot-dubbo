package com.feizi.dubbo.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by feizi on 2018/3/7.
 */
@Configuration
@PropertySource({"classpath:dubbo.properties"})
@ImportResource({"classpath:dubbo/dubbo-consumer.xml"})
public class DubboConfig {
}
