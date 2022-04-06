package channel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhangjunfeng
 * @date 2022/4/6 17:45
 */
public class ChannelTest {
    @Test
    public void fileChannelTest() throws Exception {
        String srcPath = "D:\\zhangjunfenger\\Documents\\WorkDocuments\\aaa.txt", destPath = "D:\\zhangjunfenger\\Documents\\WorkDocuments-copy\\aaa.txt";
        // 创建文件输入流
        FileInputStream fis = new FileInputStream(srcPath);
        // 获取文件管道
        FileChannel inChannel = fis.getChannel();

        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream(destPath);
        FileChannel outChannel = fos.getChannel();
        
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inChannel.read(buf) != -1) {
            // 变为读取模式
            buf.flip();
            int outlength = 0;
            // 将buf写入到输出的通道
            while ((outlength = outChannel.write(buf)) != 0) {
                System.out.println("写入的字节数：" + outlength);
            }
            // 清除buf, 变为写模式
            buf.clear();
        }
        // 强制刷盘
        outChannel.force(true);
        fis.close();
        fos.close();
        inChannel.close();
        outChannel.close();
    }
}
