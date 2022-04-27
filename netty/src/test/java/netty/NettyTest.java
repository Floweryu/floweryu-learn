package netty;

import com.floweryu.netty.demo.InHandlerDemo;
import com.floweryu.netty.pipeline.InPipeline;
import com.floweryu.netty.pipeline.OutPipeline;
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

    @Test
    public void testPipelineInBound() {
        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            protected void initChannel(EmbeddedChannel ch) {
                ch.pipeline().addLast(new InPipeline.SimpleInHandlerA());
                ch.pipeline().addLast(new InPipeline.SimpleInHandlerB());
                ch.pipeline().addLast(new InPipeline.SimpleInHandlerC());

            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(i);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        //向通道写一个入站报文
        channel.writeInbound(buf);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPipelineOutBound() {
        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            protected void initChannel(EmbeddedChannel ch) {
                ch.pipeline().addLast(new OutPipeline.SimpleOutHandlerA());
                ch.pipeline().addLast(new OutPipeline.SimpleOutHandlerB());
                ch.pipeline().addLast(new OutPipeline.SimpleOutHandlerC());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(i);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        //向通道写一个出站报文
        channel.writeOutbound(buf);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
