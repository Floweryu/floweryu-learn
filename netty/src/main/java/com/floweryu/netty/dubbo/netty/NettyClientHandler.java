package com.floweryu.netty.dubbo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author Floweryu
 * @date 2022/6/4 23:04
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    
    private ChannelHandlerContext context;
    private String result;
    private String param;
    
    /**
     * 1. 
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive 被调用");
        this.context = ctx;
    }
    
    /**
     * 4. 
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(" channelRead 被调用");
        // 获得结果
        result = msg.toString();
        notify();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    
    /**
     * 3. 发送数据给服务器 -> wait -> 等待被唤醒(channelRead) -> 返回结果
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call 开始被调用");
        // 将传入的参数写入通道
        this.context.writeAndFlush(param);
        // 等待channelRead获取到容器的结果后唤醒
        wait();
        System.out.println("call 调用结束");
        // 服务方返回结果
        return result;
    }
    
    /**
     * 2. 
     * @param param
     */
    public void setParam(String param) {
        this.param = param;
    }
}
