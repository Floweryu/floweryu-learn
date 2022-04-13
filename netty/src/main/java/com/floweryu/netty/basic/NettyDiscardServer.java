package com.floweryu.netty.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhangjunfeng
 * @date 2022/4/13 20:38
 */
public class NettyDiscardServer {
    private final int serverPort;
    
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    
    public NettyDiscardServer(int port) {
        this.serverPort = port;
    }
    
    public void runServer() {
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        
        try {
            // 1. 设置reactor线程组
            serverBootstrap.group(bossLoopGroup, workerLoopGroup);
            // 2. 设置nio类型的channel
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 3. 设置监听端口
            serverBootstrap.localAddress(serverPort);
            // 4. 设置通道参数
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
