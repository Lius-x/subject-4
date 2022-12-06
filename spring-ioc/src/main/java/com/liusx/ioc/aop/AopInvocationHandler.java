package com.liusx.ioc.aop;

import java.lang.reflect.Method;

public class AopInvocationHandler implements java.lang.reflect.InvocationHandler {

    Object target;

    public AopInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Aop before ...");
        return method.invoke(target, args);
    }
}
