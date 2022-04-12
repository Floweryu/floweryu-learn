package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjunfeng
 * @date 2022/4/12 10:22
 */
public class MthreadEchoServerReactor {
    ServerSocketChannel serverSocket;
    AtomicInteger next = new AtomicInteger(0);
    
    Selector[] selectors = new Selector[2];
    
    SubReactor[] subReactors;
    
    MthreadEchoServerReactor() throws IOException {
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocket = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(NioConfig.SOCKET_SERVER_IP, NioConfig.SOCKET_SERVER_PORT);
        serverSocket.socket().bind(address);
        serverSocket.configureBlocking(false);

        // 第一个selector负责监听新连接事件
        SelectionKey sk = serverSocket.register(selectors[0], SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());
        
        SubReactor subReactor1 = new SubReactor(selectors[0]);
        
        SubReactor subReactor2 = new SubReactor(selectors[1]);
        
        subReactors = new SubReactor[]{subReactor1, subReactor2};
        
    }
    
    private void startService() {
        new Thread(subReactors[0]).start();
        new Thread(subReactors[1]).start();
    }
    
    class SubReactor implements Runnable {
        // 每一个线程负责一个
        final Selector selector;
        
        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (! Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    for (SelectionKey sk : keySet) {
                        dispatch(sk);
                    }
                    keySet.clear();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        void dispatch(SelectionKey sk) {
            Runnable handler = (Runnable) sk.attachment();
            if (handler != null) {
                handler.run();
            }
        }
    }
    
    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                if (channel != null) {
                    new MthreadEchoHandler(selectors[next.get()], channel);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            if (next.incrementAndGet() == selectors.length) {
                next.set(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MthreadEchoServerReactor server = new MthreadEchoServerReactor();
        server.startService();
    }
}
