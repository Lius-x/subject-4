package com.liusx.ioc.xml;

import com.liusx.ioc.App;
import com.liusx.ioc.annotation.DemoComponent;
import com.liusx.ioc.util.ClassUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationApplicationContext {

    // 思路map
    static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 扫描注解，扫包 反射一个类
    // 没有id 就把类名转换成id
    // 写入map

    public Object getBean(String id) {
        if (singletonObjects.containsKey(id)) {
            return singletonObjects.get(id);
        } else {
            try {
                return doCreateBean(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private Object doCreateBean(String beanId) throws Exception {
        // 1.扫描启动类下的包
        List<Class<?>> classes = ClassUtil.getAllClassByPackageName(App.class.getPackage());
        Object o = new Object();
        for (Class<?> aClass : classes) {
            DemoComponent annotation = aClass.getAnnotation(DemoComponent.class);
            if (annotation == null) {
                continue;
            }
            String name = aClass.getSimpleName();
            String id = name.substring(0, 1).toLowerCase() + name.substring(1);
            Object instance = aClass.newInstance();
            if (id.equals(beanId)) {
                o = instance;
            }
            singletonObjects.put(id,instance);
        }
        return o;
    }
}
