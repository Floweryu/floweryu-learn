package com.floweryu.example.annotation;

import com.floweryu.example.FloweryuApplication;
import com.floweryu.example.annotation.bean.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Floweryu
 * @date 2022/1/3 21:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FloweryuApplication.class)
public class AnnotationTest {
    @Autowired
    Car car;
    
    @Test
    public void testConfigurationProperties() {
        System.out.println(car);
    }
}
