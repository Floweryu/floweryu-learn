package com.floweryu.netty.echoServer;

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
            // 1. 设置refactor线程组
            bootstrap.group(bossLoopGroup, workerLoopGroup);
            // 2. 设置nio类型的channel
            bootstrap.channel(NioServerSocketChannel.class);
            // 3. 设置监听端口
            bootstrap.localAddress(serverPort);
            // 4. 设置通道的参数
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            
            // 5. 装配子通道流水线
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                //有连接到达时会创建一个channel
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline管理子通道channel中的Handler
                    // 向子channel流水线添加一个handler处理器
                    ch.pipeline().addLast(NettyEchoServerHandler.INSTANCE);
                }
            });
            
            // 6. 绑定server
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture sync = bootstrap.bind().sync();
            System.out.println("服务器启动成功，监听端口： " + sync.channel().localAddress());

            // 7. 等待通道关闭的异步任务结束
            // 服务器监听通道一直等待通道关闭的异步任务结束
            ChannelFuture channelFuture = sync.channel().closeFuture();
            channelFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
        
    }

    public static void main(String[] args) {
        int port = NettyConfig.PORT;
        new NettyEchoServer(port).runServer();
    }
}
