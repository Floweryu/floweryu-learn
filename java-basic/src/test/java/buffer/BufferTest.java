package buffer;

import org.junit.Test;

import java.nio.IntBuffer;

/**
 * @author zhangjunfeng
 * @date 2022/4/2 17:37
 */
public class BufferTest {
    
    @Test
    public void bufferTest() {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        System.out.println("-------------读模式------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
        
        // 转换为读模式
        intBuffer.flip();
        System.out.println("-------------从写模式转换为读模式------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
    }
}
