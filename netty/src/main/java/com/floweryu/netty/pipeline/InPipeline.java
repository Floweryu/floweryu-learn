package com.floweryu.netty.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * @author zhangjunfeng
 * @date 2022/4/27 20:21
 */
public class InPipeline {
    public static class SimpleInHandlerA extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("入站处理器 A: 被回调 ");
            super.channelRead(ctx, msg);
            // 移除该处理器
            ctx.pipeline().remove(this);
        }
    }
    public static class SimpleInHandlerB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("入站处理器 B: 被回调 ");
            super.channelRead(ctx, msg);
        }
    }
    public static class SimpleInHandlerC extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("入站处理器 C: 被回调 ");
            super.channelRead(ctx, msg);
        }
    }
    
}
