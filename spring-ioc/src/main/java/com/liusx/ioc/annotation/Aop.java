package com.liusx.ioc.annotation;

import com.liusx.ioc.aop.AopBeanPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Import(AopBeanPostProcessor.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aop {
}
