package com.floweryu.example.zookeeper;

import com.floweryu.example.lock.ZkLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zhangjunfeng
 * @date 2022/3/31 17:13
 */
@Slf4j
public class LockTest extends BaseTest{
    
    ExecutorService executorService = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100, true));
    
    int count = 0;
    
    @Test
    public void testLock() throws Exception {
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                ZkLock lock = new ZkLock();
                lock.lock();
                for (int j = 0; j < 10; j++) {
                    count++;
                }
                
                try {
                    Thread.sleep(1000);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                log.info("count = {}", count);
                lock.unlock();
            });
            
        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
