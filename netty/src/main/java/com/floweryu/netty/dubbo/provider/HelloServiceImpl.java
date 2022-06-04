package com.floweryu.netty.dubbo.provider;

import com.floweryu.netty.dubbo.publicInterface.HelloService;

/**
 * @author Floweryu
 * @date 2022/6/4 22:34
 */
public class HelloServiceImpl implements HelloService {
    
    /**
     * 当消费方调用时，返回一个结果
     * @param msg
     * @return
     */
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息: " + msg);
        return msg == null ? "收到消息为null" : "收到消息=[ " + msg + " ]";
    }
}
