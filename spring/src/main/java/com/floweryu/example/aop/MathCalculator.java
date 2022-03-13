package com.floweryu.example.aop;

/**
 * @author Floweryu
 * @date 2022/3/13 15:35
 */
public class MathCalculator {
    
    public int div(int i, int j) {
        System.out.println("MathCalculator is running...");
        return i / j;
    }
}
