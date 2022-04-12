package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

/**
 * @author zhangjunfeng
 * @date 2022/4/11 14:57
 */
public class EchoClient {
    public void start() throws IOException {

        // 与EchoServerReactor类中绑定的Ip和端口相同
        InetSocketAddress address = new InetSocketAddress(NioConfig.SOCKET_SERVER_IP, NioConfig.SOCKET_SERVER_PORT);

        // 获取通道
        SocketChannel socketChannel = SocketChannel.open(address);
        
        // 非阻塞模式
        socketChannel.configureBlocking(false);
        
        // 不断自选, 等待连接完成, 或者做一些其它的事情
        while (! socketChannel.finishConnect()) {
            
        }

        System.out.println("客户端启动完成 ^_^...");
        
        Processer processer = new Processer(socketChannel);
        new Thread(processer).start();
    }
    
    static class Processer implements Runnable {
        final Selector selector;
        final SocketChannel channel;
        
        Processer(SocketChannel channel) throws IOException {
            // 新建一个选择器，这个选择器与EchoServerReactor类中的selector无关
            selector = Selector.open();
            this.channel = channel;
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
        
        public void run() {
            try {
                while (! Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> selected = selector.selectedKeys();
                    for (SelectionKey sk : selected) {
                        if (sk.isWritable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);

                            Scanner scanner = new Scanner(System.in);
                            System.out.println("请输入内容");
                            if (scanner.hasNext()) {
                                SocketChannel socketChannel = (SocketChannel) sk.channel();
                                String next = scanner.next();
                                // 向buffer中写入
                                buffer.put((" [" + new Date(System.currentTimeMillis()) + " >> " + next + "] ").getBytes(StandardCharsets.UTF_8));
                                // 转换为读取模式
                                buffer.flip();
                                // 发送数据
                                socketChannel.write(buffer);
                                buffer.clear();
                            }
                        }
                        
                        if (sk.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) sk.channel();
                            
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int length;
                            while ((length = socketChannel.read(buffer)) > 0) {
                                buffer.flip();
                                System.out.println("server echo: " + new String(buffer.array(), 0, length));
                                buffer.clear();
                            }
                        }
                    }
                    selected.clear();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException{
        new EchoClient().start();
    }
}
