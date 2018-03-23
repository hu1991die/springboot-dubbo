package com.feizi.dubbo.provider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载配置
 * Created by feizi on 2018/3/7.
 */
@Configuration
@PropertySource({"classpath:dubbo.properties"})
@ImportResource({"classpath:dubbo/dubbo-provider.xml"})
public class DubboConfig {
}
