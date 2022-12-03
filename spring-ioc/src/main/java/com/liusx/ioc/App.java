package com.liusx.ioc;

import com.liusx.ioc.service.impl.AnnotationDemoService;
import com.liusx.ioc.util.ClassUtil;
import com.liusx.ioc.xml.AnnotationApplicationContext;
import com.liusx.ioc.xml.ClassPathXmlApplicationContext;

import java.util.List;


public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        try {
            Object userService = applicationContext.getBean("UserService");
            System.out.println(userService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            List<Class<?>> allClassByPackageName = ClassUtil.getAllClassByPackageName(App.class.getPackage());
            for (Class<?> aClass : allClassByPackageName) {
                System.out.println(aClass);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 1。扫描包 -- 获取类上的注解
        // 2.判断类上的注解和我们的自定义注解一致，那就直接反射
        // 3.同xml id-- 当前类名首字母小写
        AnnotationApplicationContext context = new AnnotationApplicationContext();
        AnnotationDemoService annotationDemoService = (AnnotationDemoService) context.getBean("annotationDemoService");
        annotationDemoService.test();
    }

}
