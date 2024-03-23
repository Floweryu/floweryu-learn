package concurent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Floweryu
 * @date 2024/3/15 10:23:49
 */
public class CompletableFutureDemo {

    private String requestCF1() {
        System.out.println("执行CF1");
        return "cf1";
    }

    private String requestCF2() {
        System.out.println("执行CF2");
        return "cf2";
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行CF1");
            return "cf1";
        });
        System.out.println(cf1.join());
    }
}
