package com.floweryu.bridge;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/11/28 21:06
 */
@Data
@AllArgsConstructor
public abstract class Shape {
    protected Color color;

    //standard constructors

    abstract public String draw();
}
