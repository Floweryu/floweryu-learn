package com.spring.study.module;

import org.springframework.stereotype.Component;

/**
 * @author Floweryu
 * @date 2022/7/31 15:30
 */
@Component
public class HelloServiceImpl implements HelloService{
    
    @Override
    public String hello() {
        return "hello!!!";
    }
}
