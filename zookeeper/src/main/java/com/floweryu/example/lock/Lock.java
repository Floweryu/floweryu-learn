package com.floweryu.example.lock;

/**
 * @author zhangjunfeng
 * @date 2022/3/30 19:47
 */
public interface Lock {

    /**
     * 加锁方法
     * @return 是否成功加锁
     */
    boolean lock();

    /**
     * 解锁方法
     * @return 是否成功解锁
     */
    boolean unlock();
}
