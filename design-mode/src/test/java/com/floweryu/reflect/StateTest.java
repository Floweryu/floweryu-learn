package com.floweryu.reflect;

import com.floweryu.state.ConcreteStateA;
import com.floweryu.state.Context;
import org.junit.Test;

/**
 * @author zhangjunfeng
 * @date 2023/3/3 17:39
 */
public class StateTest {
    
    @Test
    public void test() {
        Context context = new Context();
        context.setCurrentState(new ConcreteStateA());
        context.handle1();
        context.handle2();
    }
}
