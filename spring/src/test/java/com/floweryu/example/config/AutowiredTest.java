package com.floweryu.example.config;

import com.floweryu.example.bean.Blue;
import com.floweryu.example.service.ConfigService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Floweryu
 * @date 2022/3/12 22:43
 */
public class AutowiredTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

    @Test
    public void autowiredTest() {
        ConfigService bean = context.getBean(ConfigService.class);

        Blue bean1 = context.getBean(Blue.class);
        System.out.println(bean1);
    }
}
