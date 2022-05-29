package com.floweryu.netty.protobuf;

import com.floweryu.netty.protobuf.bean.MsgProtos;

/**
 * @author Floweryu
 * @date 2022/5/29 15:42
 */
public class ProtoBufDemo {
    public static MsgProtos.Msg buildMsg() {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setConetent("zzzzzzfffff");
        builder.setId(999);
        MsgProtos.Msg message = builder.build();
        return message;
    }
}
