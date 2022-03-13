package com.floweryu.example.config;

import com.floweryu.example.aop.MathCalculator;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Floweryu
 * @date 2022/2/20 16:34
 */
public class AopTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAop.class);
    
    @Test
    public void aopTest() {
        MathCalculator bean = context.getBean(MathCalculator.class);
        bean.div(8, 2);
        context.close();
    }
}
