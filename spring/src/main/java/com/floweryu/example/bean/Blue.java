package com.floweryu.example.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Floweryu
 * @date 2022/2/27 17:53
 */
@Component
public class Blue {
    
    
    private Car car;

    @Autowired
    public Blue(Car car) {
        this.car = car;
        System.out.println("Blue....有参构造器");
    }

    // @Autowired
    public void setBook(Car car) {
       this.car = car;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "Blue{" +
                "car=" + car +
                '}';
    }
}
