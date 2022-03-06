package com.floweryu.example.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Floweryu
 * @date 2022/3/6 16:18
 */
@Component
public class Dog {
    public Dog() {
        System.out.println("Dog Construct....");
    }

    /**
     * 对象创建并赋值后使用
     */
    @PostConstruct
    public void init() {
        System.out.println("Dog @PostConstruct....");
    }

    /**
     * 容器移除对象之前使用
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Dog @PreDestroy");
    }
}
