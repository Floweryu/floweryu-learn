package com.floweryu.example.postconstruct;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Floweryu
 * @date 2021/12/27 23:39
 */
@Component
public class TestAnnotation {
    
    @PostConstruct
    private void init() {
        System.out.println("PostContruct start......");
    }
}
