package com.floweryu.example.bean;

/**
 * @author Floweryu
 * @date 2022/3/6 14:13
 */
public class Car {
    
    public Car() {
        System.out.println("construct car...");
    }
    
    public void init() {
        System.out.println("Car init....");
    }
    
    public void destory() {
        System.out.println("Car destory....");
    }
}
