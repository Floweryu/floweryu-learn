package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author zhangjunfeng
 * @date 2022/4/11 10:42
 */
public class EchoServerRefactor implements Runnable{
    Selector selector;
    ServerSocketChannel serverSocket;
    
    EchoServerRefactor() throws IOException {
        // 新建选择器
        selector = Selector.open();
        // 新建可以监听指定IP + 端口的新进来的TCP通道
        serverSocket = ServerSocketChannel.open();
        // 设为非阻塞
        serverSocket.configureBlocking(false);
        // 绑定IP和端口
        serverSocket.socket().bind(new InetSocketAddress(NioConfig.SOCKET_SERVER_IP, NioConfig.SOCKET_SERVER_PORT));
        // 将通道注册到选择器上
        /**
         * 监听的Channel通道触发了一个事件意思是该事件已经就绪。
         * “兴趣事件”的四种类型：
         *     SelectionKey.OP_CONNECT ———— 一个channel成功连接到另一个服务器，即”连接就绪“。
         *     SelectionKey.OP_ACCEPT  ———— 一个server socket channel准备好接收新进入的信道（即后文的SocketChannel）的连接操作，即”接收就绪“
         *     SelectionKey.OP_READ ———— 通道有数据可读，即”读就绪“。
         *     SelectionKey.OP_WRITE ———— 通道等待写数据，即”写就绪“。
         *  所以下面的语句意思是：
         *     serverSocketChannel被注册到选择器（selector）中，对“接收就绪”事件感兴趣，这一注册的结果封装成一个SelectionKey
         */
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        // 为selectKey关联一个处理线程
        sk.attach(new AcceptorHandler());
    }
    
    // 执行逻辑
    public void run() {
        try {
            while (! Thread.interrupted()) {
                /**
                 * 选择器选择一组SelectionKey，其相关联的信道都已做好接收I/O操作的准备
                 * 这里为什么会有一组SelectionKey？需要做出说明：
                 *    一个selector监听的ServerSocketChannel（本例只有一个，所以selected的长度为1）以及
                 *    对同一个ServerSocketChannel监听不同的兴趣事件，都会形成不同的SelectionKey。
                 */
                selector.select();
                // 返回选择器已经被上调语句选择的这组SelectionKey
                Set<SelectionKey> selected = selector.selectedKeys();
                for (SelectionKey next : selected) {
                    // 负责处理每个收到的事件
                    dispatch(next);
                }
                // 清空选择到的事件
                selected.clear();
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

    /**
     * 连接处理器
     */
    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                // 监听新进来的连接. accept方法会一直阻塞到有新连接到达
                SocketChannel channel = serverSocket.accept();
                if (channel != null) {
                    new EchoHandler(selector, channel);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerRefactor()).start();
    }
}
