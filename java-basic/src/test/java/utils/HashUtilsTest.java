package utils;

import org.junit.Test;

/**
 * @author zhangjunfeng
 * @date 2022/1/26 14:16
 */
public class HashUtilsTest {
    
    @Test
    public void tableTest() {
        String key = "50e3eea8ed0f4861837973750ed8321a";
        int hash = HashUtils.rsHash(key);
        System.out.println(hash % 32);
    }
    
    @Test
    public void weiTest() {
        int v = 0x00000008;
        int vtest = 40;
        int o = 0x00000020;
        int otest = 35;
        System.out.println(vtest & v);
        System.out.println(otest & o);
    }
    
    @Test
    public void timeTest() {
        long cur = System.currentTimeMillis();
        long one = cur + 60000;
        System.out.println(cur);
        System.out.println(one);
    }
}
