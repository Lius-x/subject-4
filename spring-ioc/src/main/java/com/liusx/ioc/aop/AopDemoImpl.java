package com.liusx.ioc.aop;

import org.springframework.stereotype.Component;

@Component
public class AopDemoImpl implements AopDemo{
    @Override
    public String getName() {
        return "aopDemo";
    }
}
