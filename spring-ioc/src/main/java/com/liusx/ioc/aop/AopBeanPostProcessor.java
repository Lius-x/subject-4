package com.liusx.ioc.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;

public class AopBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("aopDemoImpl")) {
            Class<?>[] interfaces = bean.getClass().getInterfaces();
            Object instance = Proxy.newProxyInstance(AopBeanPostProcessor.class.getClassLoader(), interfaces, new AopInvocationHandler(bean));
            return instance;
        }
        return null;
    }

}
