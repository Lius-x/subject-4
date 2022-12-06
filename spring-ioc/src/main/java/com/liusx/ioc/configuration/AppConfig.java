package com.liusx.ioc.configuration;

import com.liusx.ioc.annotation.Aop;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(value = "com.liusx.ioc")
//@EnableAspectJAutoProxy
@Aop
public class AppConfig {
}
