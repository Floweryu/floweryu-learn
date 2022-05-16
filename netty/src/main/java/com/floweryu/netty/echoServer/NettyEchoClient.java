package com.floweryu.netty.echoServer;

import com.floweryu.netty.config.NettyConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author zhangjunfeng
 * @date 2022/5/16 22:35
 */
public class NettyEchoClient {
    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public NettyEchoClient(String ip, int port) {
        this.serverPort = port;
        this.serverIp = ip;
    }
    
    public void runClient() {
        NioEventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        
        try {
            b.group(workerLoopGroup);
            b.channel(NioSocketChannel.class);
            // 这里是客户端，所以设置远程端口
            b.remoteAddress(serverIp, serverPort);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });

            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture future) -> {
                if (future.isSuccess()) {
                    System.out.println("EchoClient客户端连接成功！");
                } else {
                    System.out.println("EchoClient客户端连接失败！");
                }
            });
            
            // 阻塞，直到链接完成
            f.sync();
            Channel channel = f.channel();

            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入发送的内容：");
            
            while (scanner.hasNext()) {
                String next = scanner.next();
                byte[] bytes = (LocalDateTime.now() + " >> " + next).getBytes("UTF-8");
                //发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                System.out.println("请输入发送的内容：");
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoClient(NettyConfig.IP, NettyConfig.PORT).runClient();
    }
}
