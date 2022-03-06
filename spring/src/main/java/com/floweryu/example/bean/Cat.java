package com.floweryu.example.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Floweryu
 * @date 2022/3/6 16:09
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
    
    public Cat() {
        System.out.println("Cat construct....");
    }
    
    @Override
    public void destroy() throws Exception {
        System.out.println("Cat destroy....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat afterPropertiesSet....");
    }
}
