package concurent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjunfeng
 * @date 2023/2/14 15:50
 */
public class ThreadTest {
    private static volatile int finalI = 0;
    public static void main(String[] args) throws InterruptedException {
        
        final ExecutorService executorService = new ThreadPoolExecutor(
                2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000),
                new ThreadFactoryImpl("migrate-iss"), new ThreadPoolExecutor.CallerRunsPolicy());
        AtomicInteger failss = new AtomicInteger(0);
        AtomicInteger fail = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finall = i;
            executorService.submit(() -> {
                try {
                    //System.out.println(finall);
                    if (finall % 2 == 0) {
                        System.out.println(finall);
                        failss.getAndIncrement();
                        //success.getAndIncrement();
                    } else {
                        fail.getAndIncrement();
                    }
            
                } catch (Exception e) {
                    System.out.println(e);
                    fail.getAndIncrement();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("success: " + failss.getAndIncrement());
        System.out.println("fail: " + fail.get());
    }
}
