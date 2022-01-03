package com.floweryu.example.annotation.controller;

import com.floweryu.example.annotation.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Floweryu
 * @date 2022/1/3 22:31
 */
@RestController("/annotation")
public class AnnotationController {
    @Autowired
    Car car;
    
    @GetMapping("/car")
    public Car getCar() {
        return car;
    }
}
