package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangjunfeng
 * @date 2022/4/12 10:04
 */
public class MthreadEchoHandler implements Runnable {
    
    final SocketChannel channel;
    
    final SelectionKey sk;
    
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    
    static final int REVEVING = 0, SENDING = 1;
    int state = REVEVING;
    
    static ExecutorService pool = Executors.newFixedThreadPool(4);
    
    MthreadEchoHandler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        sk = channel.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        pool.execute(new AsyncTask());
    }
    
    public synchronized void syncRun() {
        try {
            if (state == SENDING) {
                channel.write(byteBuffer);
                // 变为写模式
                byteBuffer.clear();
                // 对读事件做监听
                sk.interestOps(SelectionKey.OP_READ);
                state = REVEVING;
                
            } else if (state == REVEVING) {
                int length;
                while ((length = channel.read(byteBuffer)) > 0) {
                    System.out.println(new String(byteBuffer.array(), 0, length));
                }
                // 转换为读模式
                byteBuffer.flip();
                // 对写事件做监听
                sk.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    class AsyncTask implements Runnable {
        @Override
        public void run() {
            MthreadEchoHandler.this.syncRun();
        }
    }
}
