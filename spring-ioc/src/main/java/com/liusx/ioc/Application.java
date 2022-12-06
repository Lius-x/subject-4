package com.liusx.ioc;

import com.liusx.ioc.aop.AopDemo;
import com.liusx.ioc.configuration.AnnotationConfiguration;
import com.liusx.ioc.configuration.AppConfig;
import com.liusx.ioc.service.Members;
import com.liusx.ioc.service.OrderService;
import com.liusx.ioc.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {
    public static void main(String[] args) throws Exception {
        // xml获取bean
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("IOC is starting..");
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        System.out.println("spring Bean:" + orderService + "Result" + orderService.getOrderName());

        // 注解获取bean
        AnnotationConfigApplicationContext annotationConfiguration = new AnnotationConfigApplicationContext(AnnotationConfiguration.class);
        System.out.println("spring Bean:" + annotationConfiguration.getBean(UserService.class));

        // 扫描包获取Bean
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(AppConfig.class);
        annotationConfigApplicationContext.refresh();
        System.out.println("spring Bean:" + annotationConfigApplicationContext.getBean(Members.class));

        // AOP
        UserService bean = annotationConfigApplicationContext.getBean(UserService.class);
        System.out.println(bean.getUserName());

        // 自定义动态代理
        AopDemo aopDemo = annotationConfigApplicationContext.getBean(AopDemo.class);
        System.out.println(aopDemo.getName());

    }
}
