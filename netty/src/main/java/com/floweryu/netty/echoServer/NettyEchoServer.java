package com.floweryu.netty.echoServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author Floweryu
 * @date 2022/5/15 16:26
 */
public class NettyEchoServer {

    private final int serverPort;

    ServerBootstrap bootstrap = new ServerBootstrap();

    public NettyEchoServer(int port) {
        this.serverPort = port;        
    }
    
    public void runServer() {
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        
        try {
            bootstrap.group(bossLoopGroup, workerLoopGroup);
        }
    }
}
