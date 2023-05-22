package concurent;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/05/22
 */
public class FutureGet {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<?> future = executorService.submit(() -> {
            try {
                demo();
            } catch (InterruptedException e) {
                System.out.println(new Date() + "," + Thread.currentThread().getName() + ", Interrupted:" + e);
                throw new RuntimeException(e);
            }
        });

        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(new Date() + "," + threadName + "获取的结果 -- start");
            Object result = future.get(2000, TimeUnit.MILLISECONDS);
            System.out.println(new Date() + "," + threadName + "获取的结果 -- end :" + result);
        } catch (Exception e) {
            System.out.println(new Date() + "," + threadName + "获取的结果异常:" + e);
        }
        future.cancel(true);
        System.out.println(new Date() + "," + threadName + "获取的结果 -- cancel");
    }

    private static Object demo() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(new Date() + "," + threadName + ",执行 demo -- start");
        Thread.sleep(5000);
        System.out.println(new Date() + "," + threadName + ",执行 demo -- end");
        return "test";
    }
}
