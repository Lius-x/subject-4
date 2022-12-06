package com.liusx.ioc.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectDemo {
    // 可以理解为切面，这就是切面
    @Pointcut("execution(* com.liusx.ioc.service.*.*(..))")
    public void Transfer() {

    }

    @Before("Transfer()")
    public void before1() {
        System.out.println("before");
    }
}
