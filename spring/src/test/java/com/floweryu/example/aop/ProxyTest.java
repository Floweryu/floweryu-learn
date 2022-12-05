package com.floweryu.example.aop;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Floweryu
 * @date 2022/11/23 17:13
 */
public class ProxyTest {
    
    @Test
    public void testProxy() {
        // 实例化目标对象
        UserService userService = new UserServiceImpl();
        
        // 实例化代理对象
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
        
        // 根据目标对象生成代理对象
        UserService proxy = (UserService) invocationHandler.getProxy();
        
        // 调用代理对象的方法
        proxy.add();
    }
    
    @Test
    public void testAssignable() {
        System.out.println(ArrayList.class.isAssignableFrom(Object.class));
        
        System.out.println(Object.class.isAssignableFrom(ArrayList.class));
    }
}
