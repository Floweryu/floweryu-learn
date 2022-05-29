package netty;

import com.floweryu.netty.decode.StringProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author zhangjunfeng
 * @date 2022/5/18 11:10
 */
public class LineBasedFrameDecoderTest {
    public static final int MAGICCODE = 9999;
    public static final int VERSION = 100;
    static String spliter = "\r\n";
    static String spliter2 = "\t";
    static String content = "zzzzzzzfffffff";

    @Test
    public void testLineBasedFrameDecoder() {
        try {
            ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
                protected void initChannel(EmbeddedChannel ch) {
                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };
            EmbeddedChannel channel = new EmbeddedChannel(i);

            for (int j = 0; j < 100; j++) {

                //1-3之间的随机数
                Random r = new Random();
                int random = Math.abs(r.nextInt(3)) + 1;
                ByteBuf buf = Unpooled.buffer();
                for (int k = 0; k < random; k++) {
                    buf.writeBytes(content.getBytes(StandardCharsets.UTF_8));
                }
                // 在每个数据包后面加上换行符
                buf.writeBytes(spliter.getBytes(StandardCharsets.UTF_8));
                channel.writeInbound(buf);
            }


            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
