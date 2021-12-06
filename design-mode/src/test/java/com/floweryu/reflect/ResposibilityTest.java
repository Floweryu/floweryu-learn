package com.floweryu.reflect;

import com.floweryu.responsibility.ConcreteHandler1;
import com.floweryu.responsibility.ConcreteHandler2;
import com.floweryu.responsibility.ConcreteHandler3;
import com.floweryu.responsibility.Handler;
import org.junit.Test;

/**
 * @author Floweryu
 * @date 2021/11/28 21:35
 */
public class ResposibilityTest {
    @Test
    public void testResposibility() {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setSuccessor(handler2);
        handler2.setSuccessor(handler3);
        
        int[] arr = new int[]{2, 12, 22, 32};
        
        for (int a : arr) {
            handler1.handleRequest(a);
        }
    }
}
