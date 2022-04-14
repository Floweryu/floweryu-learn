package com.floweryu.netty.basic;

import com.floweryu.netty.config.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
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
            
            // 5. 装配子通道流水线
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyDiscardHandler());
                }
            });
            // 6. 开始绑定server, 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("服务器启动成功，监听端口： " + channelFuture.channel().localAddress());
            
            // 7. 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // 8. 释放掉所有资源 包括创建的线程
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyDiscardServer(NettyConfig.PORT).runServer();
    }
}
