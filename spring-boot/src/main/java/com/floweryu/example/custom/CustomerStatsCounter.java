package com.floweryu.example.custom;

import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.github.benmanes.caffeine.cache.stats.StatsCounter;
import org.checkerframework.checker.index.qual.NonNegative;

import java.util.concurrent.atomic.LongAdder;

import static java.util.Objects.requireNonNull;

/**
 * @Author zhangjunfeng
 * @Date 2024/10/29 22:58
 */
public class CustomerStatsCounter implements StatsCounter {

    private final LongAdder hitCount;
    private final LongAdder missCount;
    private final LongAdder loadSuccessCount;
    private final LongAdder loadFailureCount;
    private final LongAdder totalLoadTime;
    private final LongAdder evictionCount;
    private final LongAdder evictionWeight;

    /**
     * Constructs an instance with all counts initialized to zero.
     */
    public CustomerStatsCounter() {
        hitCount = new LongAdder();
        missCount = new LongAdder();
        loadSuccessCount = new LongAdder();
        loadFailureCount = new LongAdder();
        totalLoadTime = new LongAdder();
        evictionCount = new LongAdder();
        evictionWeight = new LongAdder();
    }

    @Override
    public void recordHits(int count) {
        hitCount.add(count);
        System.out.println("hitCount: " + hitCount);
    }

    @Override
    public void recordMisses(int count) {
        missCount.add(count);
        System.out.println("missCount: " + missCount);
    }

    @Override
    public void recordLoadSuccess(long loadTime) {
        loadSuccessCount.increment();
        totalLoadTime.add(loadTime);
        System.out.println("loadSuccessCount: " + loadSuccessCount);
        System.out.println("totalLoadTime: " + totalLoadTime);
    }

    @Override
    public void recordLoadFailure(long loadTime) {
        loadFailureCount.increment();
        totalLoadTime.add(loadTime);
        System.out.println("loadFailureCount: " + loadFailureCount);
        System.out.println("totalLoadTime: " + totalLoadTime);
    }

    @Override
    public void recordEviction(int weight, RemovalCause cause) {
        requireNonNull(cause);
        evictionCount.increment();
        evictionWeight.add(weight);
        System.out.println("evictionCount: " + evictionCount);
        System.out.println("evictionWeight: " + evictionWeight);
    }

    public @NonNegative long requestCount() {
        return saturatedAdd(hitCount.sum(), missCount.sum());
    }

    public @NonNegative double hitRate() {
        long requestCount = requestCount();
        return (requestCount == 0) ? 1.0 : (double) hitCount.sum() / requestCount;
    }

    public @NonNegative double missRate() {
        long requestCount = requestCount();
        return (requestCount == 0) ? 0.0 : (double) missCount.sum() / requestCount;
    }

    @Override
    public CacheStats snapshot() {
        return CacheStats.of(
                negativeToMaxValue(hitCount.sum()),
                negativeToMaxValue(missCount.sum()),
                negativeToMaxValue(loadSuccessCount.sum()),
                negativeToMaxValue(loadFailureCount.sum()),
                negativeToMaxValue(totalLoadTime.sum()),
                negativeToMaxValue(evictionCount.sum()),
                negativeToMaxValue(evictionWeight.sum()));
    }

    /** Returns {@code value}, if non-negative. Otherwise, returns {@link Long#MAX_VALUE}. */
    private static long negativeToMaxValue(long value) {
        return (value >= 0) ? value : Long.MAX_VALUE;
    }

    /**
     * Increments all counters by the values in {@code other}.
     *
     * @param other the counter to increment from
     */
    public void incrementBy(StatsCounter other) {
        CacheStats otherStats = other.snapshot();
        hitCount.add(otherStats.hitCount());
        missCount.add(otherStats.missCount());
        loadSuccessCount.add(otherStats.loadSuccessCount());
        loadFailureCount.add(otherStats.loadFailureCount());
        totalLoadTime.add(otherStats.totalLoadTime());
        evictionCount.add(otherStats.evictionCount());
        evictionWeight.add(otherStats.evictionWeight());
    }

    private static long saturatedAdd(long a, long b) {
        long naiveSum = a + b;
        if (((a ^ b) < 0) | ((a ^ naiveSum) >= 0)) {
            // If a and b have different signs or a has the same sign as the result then there was no
            // overflow, return.
            return naiveSum;
        }
        // we did over/under flow, if the sign is negative we should return MAX otherwise MIN
        return Long.MAX_VALUE + ((naiveSum >>> (Long.SIZE - 1)) ^ 1);
    }

    @Override
    public String toString() {
        return snapshot().toString();
    }
}
