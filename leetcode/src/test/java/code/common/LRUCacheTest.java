package code.common;

import org.junit.Test;

/**
 * @author zhangjunfeng
 * @date 2023/2/6 19:41
 */
public class LRUCacheTest {
    
    @Test
    public void test() {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put(1, 11);
        lruCache.put(2, 22);
        lruCache.put(3, 33);
        lruCache.put(4, 44);
        lruCache.put(5, 55);
        lruCache.print();
        int i = lruCache.get(1);
        lruCache.print();
        lruCache.put(6, 66);
        lruCache.print();
    }
}
