package com.liusx.ioc.configuration;

import com.liusx.ioc.service.UserService;
import com.liusx.ioc.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfiguration {
    // 加载一些Jar bean --
    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }
}
