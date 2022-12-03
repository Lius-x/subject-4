package com.liusx.ioc.service.impl;

import com.liusx.ioc.annotation.DemoComponent;

@DemoComponent
public class AnnotationDemoService {
    public void test(){
        System.out.println("hello world!");
    }
}
