package com.floweryu.example.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Floweryu
 * @date 2022/2/20 16:34
 */
public class ConfigTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
    
    private void printNames() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
    
    @Test
    public void importTest() {
        printNames();
    }

    @Test
    public void config2Test() {
        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        System.out.println("容器创建完成");
        Object bean = context.getBean("book");
    }
    
    @Test
    public void configTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
