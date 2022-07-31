package com.floweryu.example.starter.controller;

import com.spring.study.module.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Floweryu
 * @date 2022/7/31 15:51
 */
@RestController
public class StarterController {
    
    @Autowired
    private HelloService helloService;
    
    @GetMapping("/starter/test")
    public String home() {
        return helloService.hello();
    }
}
