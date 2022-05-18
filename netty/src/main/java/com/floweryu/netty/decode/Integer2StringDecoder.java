package com.floweryu.netty.decode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author zhangjunfeng
 * @date 2022/5/18 10:39
 */
public class Integer2StringDecoder extends MessageToMessageDecoder<Integer> {

    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        // 实参为Integer，明确了入站类型为Integer，然后在解码时将Integer转为字符串，发送给下一个入站处理器
        out.add(String.valueOf(msg));
    }
}
