package com.floweryu.netty.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author zhangjunfeng
 * @date 2022/5/17 20:31
 */
public class IntegerAddDecoder extends ReplayingDecoder<IntegerAddDecoder.Status> {
    
    enum Status {
        PARSE_1, PARSE_2
    }
    
    private Integer first;
    private Integer second;
    
    public IntegerAddDecoder() {
        //构造函数中，需要初始化父类的state 属性，表示当前阶段
        super(Status.PARSE_1);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case PARSE_1:
                // 从ByteBuf中读取数据
                first = in.readInt();
                System.out.println("first = " + first);
                // 进入第二步，设置读指针断点为当前读取位置
                checkpoint(Status.PARSE_2);
                break;
            case PARSE_2:
                second = in.readInt();
                System.out.println("second = " + second);
                Integer sum = first + second;
                System.out.println("sum = " + sum);
                out.add(sum);
                checkpoint(Status.PARSE_1);
                break;
            default:
                break;
        }
    }

}
