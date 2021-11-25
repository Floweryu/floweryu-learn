package com.floweryu.reflect;

import com.floweryu.reflect.statics.GreetingAndBye;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangjunfeng
 * @date 2021/11/24 15:17
 */
public class ReflectTest {
    /**
     * 获取public方法
     */
    @Test
    public void invokePublicMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<GreetingAndBye> clazz = GreetingAndBye.class;
        Method method = clazz.getMethod("greeting", String.class);

        Object result = method.invoke(null, "Eric");

        System.out.println(result);
    }

    @Test
    public void invokePrivateMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<GreetingAndBye> clazz = GreetingAndBye.class;
        Method method = clazz.getDeclaredMethod("goodBye", String.class);
        method.setAccessible(true);

        Object result = method.invoke(null, "Eric");

        System.out.println(result);
    }
}
