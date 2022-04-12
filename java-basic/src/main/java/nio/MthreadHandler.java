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
 * @date 2022/4/11 20:27
 */
public class MthreadHandler implements Runnable{
    final SocketChannel channel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(10240);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;
    
    ExecutorService pool = Executors.newFixedThreadPool(2);
    static final int PROCESSING = 3;
    
    MthreadHandler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        selectionKey = channel.register(selector, 0);
        
        selectionKey.attach(this);
        
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }
    
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    synchronized void read() throws IOException {
        channel.read(input);
        if (inputIsCompleted()) {
            state = PROCESSING;
            pool.execute(new Processer());
        }
    }
    
    void send() throws IOException {
        channel.write(output);
        
        if (outputIsCompleted()) {
            selectionKey.cancel();
        }
    }
    
    boolean inputIsCompleted() {
        return true;
    }
    
    boolean outputIsCompleted() {
        return true;
    }
    
    void process() {
        
    }
    
    synchronized void processAndHandOff() {
        process();
        state = SENDING;
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }
    
    class Processer implements Runnable {
        @Override
        public void run() {
            processAndHandOff();
        }
    }
}
