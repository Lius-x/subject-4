package com.liusx.ioc.configuration;

import com.liusx.ioc.annotation.DemoComponent;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.liusx.ioc.service")
public class AppConfig {
}
