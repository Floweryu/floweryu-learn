package com.floweryu.example.annotation.controller;

import com.floweryu.example.annotation.bean.Car;
import com.floweryu.example.annotation.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Floweryu
 * @date 2022/1/3 22:31
 */
@RestController
@RequestMapping("/annotation")
public class AnnotationController {
    @Autowired
    Car car;
    
    @Autowired
    Person person;
    
    @GetMapping("/car")
    public Car getCar() {
        return car;
    }
    
    @GetMapping("/person")
    public Person getPerson() {
        return person;
    }
}
