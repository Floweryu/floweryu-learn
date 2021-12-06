package com.floweryu.bridge;

/**
 * @author Floweryu
 * @date 2021/11/28 21:06
 */
public class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return "Square drawn. " + color.fill();
    }
}
