package netty;

import com.floweryu.netty.demo.InHandlerDemo;
import com.floweryu.netty.pipeline.InPipeline;
import com.floweryu.netty.pipeline.OutPipeline;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

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

    @Test
    public void testPipelineHotBound() {
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
        channel.writeInbound(buf);
        channel.writeInbound(buf);
        channel.writeInbound(buf);
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeReadTest() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        System.out.println("分配ByteBuf: " + buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        System.out.println("写入字符: " + buffer);

        // 读取字符，不改变指针
        for (int i = 0; i < buffer.readableBytes(); i++) {
            System.out.println("读取到的字符: " + buffer.getByte(i));
        }
        System.out.println("读完字符， buffer: " + buffer);

        while (buffer.isReadable()) {
            System.out.println("取一个字符： " + buffer.readByte());
        }
        System.out.println("取完字符， buffer: " + buffer);
    }

    @Test
    public void refTest() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        System.out.println("创建后: " + buffer.refCnt());

        buffer.retain();
        System.out.println("retain后： " + buffer.refCnt());

        buffer.release();
        System.out.println("release后： " + buffer.refCnt());

        buffer.release();
        System.out.println("release后： " + buffer.refCnt());

        buffer.retain();
        System.out.println("retain后： " + buffer.refCnt());
    }

    final static Charset UTF_8 = Charset.forName("UTF-8");

    //堆缓冲区
    @Test
    public  void testHeapBuffer() {
        //取得堆内存
        //取得堆内存--netty4默认直接buffer，而非堆buffer
        //ByteBuf heapBuf = ByteBufAllocator.DEFAULT.buffer();
        ByteBuf heapBuf = ByteBufAllocator.DEFAULT.heapBuffer();
        heapBuf.writeBytes("hello world".getBytes(UTF_8));
        if (heapBuf.hasArray()) {
            //取得内部数组
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            System.out.println(offset);
            int length = heapBuf.readableBytes();
            System.out.println(heapBuf);
            System.out.println(new String(array, offset, length, UTF_8));
        }
        heapBuf.release();

    }

    //直接缓冲区
    @Test
    public  void testDirectBuffer() {
        ByteBuf directBuf =  ByteBufAllocator.DEFAULT.directBuffer();
        directBuf.writeBytes("hello world".getBytes(UTF_8));
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            //读取数据到堆内存
            directBuf.getBytes(directBuf.readerIndex(), array);
            System.out.println(new String(array, UTF_8));
        }
        directBuf.release();
    }

    @Test
    public void intCompositeBufComposite() {
        CompositeByteBuf cbuf = Unpooled.compositeBuffer(3);
        cbuf.addComponent(Unpooled.wrappedBuffer(new byte[]{1, 2, 3}));
        cbuf.addComponent(Unpooled.wrappedBuffer(new byte[]{4}));
        cbuf.addComponent(Unpooled.wrappedBuffer(new byte[]{5, 6}));
        //合并成一个单独的缓冲区
        ByteBuffer nioBuffer = cbuf.nioBuffer(0, 6);
        byte[] bytes = nioBuffer.array();
        System.out.print("bytes = ");
        for (byte b : bytes) {
            System.out.print(b);
        }
        cbuf.release();
    }
}
