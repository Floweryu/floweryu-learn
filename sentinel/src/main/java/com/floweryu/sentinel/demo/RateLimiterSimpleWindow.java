package com.floweryu.sentinel.demo;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjunfeng
 * @date 2022/8/9 20:31
 */
public class RateLimiterSimpleWindow {
    /**
     * 阈值
     */
    private static Integer QPS = 2;
    
    /**
     * 时间窗口
     */
    private static long TIME_WINDOWS = 1000;
    
    /**
     * 计数器
     */
    private static AtomicInteger REQ_COUNT = new AtomicInteger();
    
    private static long START_TIME = System.currentTimeMillis();
    
    public synchronized static boolean tryAcquire() {
        // 如果超出时间窗口，重置计数器为0，并重新记录时间窗口起始时间
        if (System.currentTimeMillis() - START_TIME > TIME_WINDOWS) {
            System.out.println("超出时间窗口");
            REQ_COUNT.set(0);
            START_TIME = System.currentTimeMillis();
        }
        // 计数器+1，判断是否达到阈值
        return REQ_COUNT.incrementAndGet() <= QPS;
    }
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(250);
            LocalTime now = LocalTime.now();
            if (!tryAcquire()) {
                System.out.println(now + " 被限流");
            } else {
                System.out.println(now + " 做点什么");
            }
        }
    }
}
