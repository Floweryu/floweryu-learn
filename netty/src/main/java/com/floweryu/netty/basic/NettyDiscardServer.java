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
        // 用来监听IO时间的线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        // 用来负责IO事件和Handler业务处理
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        
        try {
            // 1. 设置reactor线程组
            // 也可以设置一个：这样监听和处理线程都是一个，易被阻塞
            serverBootstrap.group(bossLoopGroup, workerLoopGroup);
            // 2. 设置nio类型的channel
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 3. 设置监听端口
            serverBootstrap.localAddress(serverPort);
            // 4. 设置通道参数, 给父通道设置
            // SO_KEEPALIVE 开启TCP底层心跳机制
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            // 给子通道设置参数
            serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            
            // 5. 装配子通道流水线
            // 在父通道成功接收一个连接并创建一个子通道后, 就会调用initChannel初始化子通道, 然后向子通道流水线增加业务处理器
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
