package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author zhangjunfeng
 * @date 2022/4/11 10:55
 */
public class EchoHandler implements Runnable{
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECIEVING = 0, SENDING = 1;
    int state = RECIEVING;
    
    EchoHandler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        /**
         * 把SocketChannel注册到EchoServerReactor类中的同一个选择器中，返回选择键
         * 这个register方法的代码在AbstractSelectableChannel中，其中含有位运算，参数ops不传0则产生参数异常；
         * 传0则表示暂不注册任何兴趣事件
         */
        sk = channel.register(selector, 0);
        // 将Handler自身作为选择键的附件
        sk.attach(this);
        // 注册Read就绪事件，当SocketChannel处于可读状态时，就可以读取其内容
        sk.interestOps(SelectionKey.OP_READ);
        // 使到目前为止还未返回的第一个选择操作立刻返回结果
        selector.wakeup();
    }
    
    public void run() {
        try {
            if (state == SENDING) {
                // 从buffer中读取数据写入通道
                channel.write(byteBuffer);
                // 写完后，开始从通道读，buffer切换为写模式
                byteBuffer.clear();
                // 写完后，注册read事件
                sk.interestOps(SelectionKey.OP_READ);
                // 写完后 转为接收状态
                state = RECIEVING;
            } else if (state == RECIEVING) {
                // 从通道读
                int length;
                while ((length = channel.read(byteBuffer)) > 0) {
                    System.out.println(new String(byteBuffer.array(), 0, length));
                }
                // 读完后，准备写入通道，buffer切换为读取模式
                byteBuffer.flip();
                sk.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
