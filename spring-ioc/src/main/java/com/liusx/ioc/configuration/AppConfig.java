package com.liusx.ioc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(value = "com.liusx.ioc")
@EnableAspectJAutoProxy
public class AppConfig {
}
