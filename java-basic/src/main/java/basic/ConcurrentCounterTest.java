package basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentCounterTest {

    public static void main(String[] args) throws Throwable {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ConcurrentCounter concurrentCounter = new ConcurrentCounter();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    concurrentCounter.increase();
                }
            });
        }

//        executorService.shutdown();
        System.out.println(concurrentCounter.getCount());
    }
}



