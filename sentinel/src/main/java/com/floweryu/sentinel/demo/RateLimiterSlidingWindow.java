package com.floweryu.sentinel.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Floweryu
 * @date 2022/8/14 17:27
 */
public class RateLimiterSlidingWindow {
    /**
     * 阈值
     */
    private int qps = 2;
    /**
     * 时间窗口总大小（毫秒）
     */
    private long windowSize = 1000;
    /**
     * 多少个子窗口
     */
    private Integer windowCount = 10;
    
    private WindowInfo[] windowArray = new WindowInfo[windowCount];
    
    public RateLimiterSlidingWindow(int qps) {
        this.qps = qps;
        long currentTimeMills = System.currentTimeMillis();
        for (int i = 0; i < windowArray.length; i++) {
            windowArray[i] = new WindowInfo(currentTimeMills, new AtomicInteger(0));
        }
    }
    
    public synchronized boolean tryAcquire() {
        long currentTimeMills = System.currentTimeMillis();
        // 计算当前时间窗口
        // 1003 % 1000 / (1000 / 10) = 3 / 100 = 0
        int currentIndex = (int) (currentTimeMills % windowSize / (windowSize / windowCount));
        // 更新当前窗口计数 & 重置过期窗口计数
        int sum = 0;
        for (int i = 0; i < windowArray.length; i++) {
            WindowInfo windowInfo = windowArray[i];
            if ((currentTimeMills - windowInfo.getTime()) > windowSize) {
                windowInfo.getNumber().set(0);
                windowInfo.setTime(currentTimeMills);
            }
            if (currentIndex == i && windowInfo.getNumber().get() < qps) {
                windowInfo.getNumber().incrementAndGet();
            }
            sum += windowInfo.getNumber().get();
        }
        // 当前qps是否超过限制
        return sum <= qps;
    }
    
    private static class WindowInfo {
        /**
         * 窗口开始时间
         */
        private Long time;
    
        /**
         * 计数器
         */
        private AtomicInteger number;
        
        public WindowInfo(long time, AtomicInteger number) {
            this.time = time;
            this.number = number;
        }
    
        public Long getTime() {
            return time;
        }
    
        public AtomicInteger getNumber() {
            return number;
        }
    
        public void setTime(Long time) {
            this.time = time;
        }
    
        public void setNumber(AtomicInteger number) {
            this.number = number;
        }
    }
}
