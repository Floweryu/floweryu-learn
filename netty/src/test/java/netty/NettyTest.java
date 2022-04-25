package netty;

import com.floweryu.netty.demo.InHandlerDemo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @author zhangjunfeng
 * @date 2022/4/25 20:38
 */
public class NettyTest {
    @Test
    public void inHandlerDemoTest() {
        final InHandlerDemo inHandlerDemo = new InHandlerDemo();

        ChannelInitializer initializer = new ChannelInitializer() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(inHandlerDemo);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(11);
        channel.writeInbound(buf);
        channel.flush();
        channel.writeInbound(buf);
        channel.flush();
        
        channel.close();
        
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
