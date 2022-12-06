package com.liusx.ioc.service.impl;


import com.liusx.ioc.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


public class UserServiceImpl implements UserService {


    @Override
    public String getUserName() {
        return "kane";
    }

    public UserServiceImpl() {
        //构造方法，本方法执行，代表本类被创建
        System.out.println("此时，类已经实例化了。");
    }

    @PostConstruct //Bean初始化完成后的回调，会执行这个方法
    public void init() {
        System.out.println("此时，Bean已经被初始化了。");
    }
}
