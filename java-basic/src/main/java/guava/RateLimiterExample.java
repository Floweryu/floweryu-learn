package guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;

/**
 * @author zhangjunfeng
 * @date 2023/2/28 11:34
 */
public class RateLimiterExample {
    private static final RateLimiter rateLimiter = RateLimiter.create(2);
    
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            double acquire = rateLimiter.acquire();
            System.out.println("cutTime=" + new Date() + " acq:" + i + " waitTime:" + acquire);
        }
    }
}
