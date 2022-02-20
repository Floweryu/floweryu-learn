package com.floweryu.example.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Floweryu
 * @date 2022/2/20 16:34
 */
public class ConfigTest {
    
    @Test
    public void configTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
