package com.floweryu.netty.dubbo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Floweryu
 * @date 2022/6/4 23:21
 */
public class NettyClient {
    
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    
    private static NettyClientHandler clientHandler;
    
    public Object getBean(final Class<?> serviceClass, final String providerName) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
                new Class<?>[] {serviceClass}, ((proxy, method, args) -> {
                    if (clientHandler == null) {
                        initClient();
                    }
                    // providerName是协议头   args[0] 是客户端调用接口的参数
                    clientHandler.setParam(providerName + args[0]);
                    return executorService.submit(clientHandler).get();
                })
        );
        
    }
    
    private static void initClient() {
        clientHandler = new NettyClientHandler();
    
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(
                        new ChannelInitializer<SocketChannel>() {
    
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast(new StringDecoder());
                                pipeline.addLast(new StringEncoder());
                                pipeline.addLast(clientHandler);
                            }
                        }
                );
        
        try {
            bootstrap.connect("127.0.0.1", 9999);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
