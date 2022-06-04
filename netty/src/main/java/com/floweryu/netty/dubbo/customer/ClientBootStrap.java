package com.floweryu.netty.dubbo.customer;

import com.floweryu.netty.dubbo.netty.NettyClient;
import com.floweryu.netty.dubbo.publicInterface.HelloService;

/**
 * @author Floweryu
 * @date 2022/6/4 23:39
 */
public class ClientBootStrap {
    
    public static final String providerName = "HelloService#hello#";
    
    public static void main(String[] args) {
        NettyClient customer = new NettyClient();
    
        HelloService bean = (HelloService) customer.getBean(HelloService.class, providerName);
    
        String res = bean.hello("hello dubbo");
    
        System.out.println("调用的结果: [ " + res + " ]");
    }
}
