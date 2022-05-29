package netty;

import com.floweryu.netty.protobuf.ProtoBufDemo;
import com.floweryu.netty.protobuf.bean.MsgProtos;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * @author Floweryu
 * @date 2022/5/29 15:48
 */
public class ProtoBufDemoTest {
    @Test
    public void serAndDesr1() throws Exception {
        MsgProtos.Msg msg = ProtoBufDemo.buildMsg();
    
        byte[] bytes = msg.toByteArray();
    
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(bytes);
        bytes = outputStream.toByteArray();
        MsgProtos.Msg msg1 = MsgProtos.Msg.parseFrom(bytes);
        System.out.println(msg1);
    }
}
