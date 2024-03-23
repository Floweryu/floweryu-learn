package basic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Floweryu
 * @date 2024/3/22 16:56:13
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Future future = executorService.submit(() -> {
            try {
                sayHi("submit");
            } catch (Exception e) {
                System.out.println("sayHi Exception");
                e.printStackTrace();
            }
        });

        try {
            future.get();
        } catch (Exception e) {
            System.out.println("future.get Exception");
            e.printStackTrace();
        }
    }

    private static void sayHi(String name) throws RuntimeException {
        String printStr = "【thread-name:" + Thread.currentThread().getName() + ",执行方式:" + name + "】";
        System.out.println(printStr);
        throw new RuntimeException(printStr + ",我异常啦!哈哈哈!");
    }
}
