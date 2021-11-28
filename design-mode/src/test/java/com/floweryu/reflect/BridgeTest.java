package com.floweryu.reflect;

import com.floweryu.bridge.Blue;
import com.floweryu.bridge.Shape;
import com.floweryu.bridge.Square;
import org.junit.Test;

/**
 * @author Floweryu
 * @date 2021/11/28 21:07
 */
public class BridgeTest {
    @Test
    public void testBridge() {
        //a square with red color
        Shape square = new Square(new Blue());

        System.out.println(square.draw());
    }
}
