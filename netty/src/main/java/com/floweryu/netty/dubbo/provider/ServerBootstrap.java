package com.floweryu.netty.dubbo.provider;

import com.floweryu.netty.dubbo.netty.NettyServer;

/**
 * 启动一个服务提供者, 即netty
 * @author Floweryu
 * @date 2022/6/4 22:38
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1", 9999);
    }
}
