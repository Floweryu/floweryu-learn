package com.floweryu.example.lock;

import com.floweryu.example.bean.factory.ClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjunfeng
 * @date 2022/3/30 19:51
 */
@Slf4j
public class ZkLock implements Lock{

    /**
     * 加锁的节点前缀
     */
    private static final String ZK_PATH = "/test/lock";

    /**
     * 临时序号节点的前缀
     */
    private static final String SEQ = "seq";

    /**
     * 临时序号节点路径
     */
    private static final String LOCK_PREFIX = ZK_PATH + "/" + SEQ;
    private static final long WAIT_TIME = 1000;
    
    CuratorFramework client;
    /**
     * 加锁的节点编号
     */
    private String lockedShortPath = null;
    /**
     * 加锁的节点路径
     */
    private String lockedPath = null;

    /**
     * 加锁节点的前一个节点路径
     */
    private String priorPath = null;
    final AtomicInteger lockCount = new AtomicInteger(0);
    private Thread thread;
    
    public ZkLock() {
        synchronized (this) {
            client = ClientFactory.createSimple("106.15.42.148:2181");
            client.start();
            try {
                if (null == client.checkExists().forPath(ZK_PATH)) {
                    client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(ZK_PATH);
                }
            } catch(Exception e) {
                log.error("ZkLock init error", e);
            }
        }
    }
    
    /**
     * 加锁方法
     *
     * @return 是否成功加锁
     */
    @Override
    public boolean lock() {
        synchronized (this) {
            if (lockCount.get() == 0) {
                thread = Thread.currentThread();
                lockCount.incrementAndGet();
            } else {
                if (!thread.equals(Thread.currentThread())) {
                    return false;
                }
                lockCount.incrementAndGet();
                return true;
            }
        }
        try {
            // 尝试加锁
            boolean locked = tryLock();
            if (locked) {
                return true;
            }
            
            // 如果加锁失败, 继续等待
            while (true) {
                await();

                // 获取等待的子节点编号列表
                List<String> waiters = getWaiters();
                log.info("等待时所有子节点: {}", waiters);
                // 判断是否加锁成功
                if (checkLock(waiters)) {
                    return true;
                }
            }
        } catch(Exception e) {
            log.error("lock error", e);
            unlock();
        }
        return false;
    }

    /**
     * 解锁方法
     *
     * @return 是否成功解锁
     */
    @Override
    public boolean unlock() {
        // 只有加锁的线程才能释放锁
        if (! thread.equals(Thread.currentThread())) {
            return false;
        }
        int newLockCount = lockCount.decrementAndGet();
        // 计数不能小于0
        if (newLockCount < 0) {
            throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + lockedPath);
        }
        //如果计数不为0，直接返回
        if (newLockCount != 0) {
            return true;
        }
        //删除临时节点
        try {
            if (client.checkExists().forPath(lockedPath) != null) {
                client.delete().forPath(lockedPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    private boolean tryLock() throws Exception {
        // 创建临时顺序节点
        lockedPath = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(LOCK_PREFIX);
        if (null == lockedPath) {
            throw new Exception("创建临时顺序节点失败");
        }

        // 获取所有子节点
        List<String> waiters = getWaiters();
        log.info("加锁时所有子节点序号: {}", waiters);
        
        // 获取加锁的排队编号
        lockedShortPath = getShortPath(lockedPath);
        log.info("需要加锁的节点编号={}", lockedShortPath);
        // 判断自己是否为第一个
        if (checkLock(waiters)) {
            return true;
        }
        
        // 判断自己在第几个
        int index = Collections.binarySearch(waiters, lockedShortPath);
        if (index < 0) {
            throw new Exception("没有找到对应节点, path=" + lockedShortPath);
        }

        // 获取前一个节点的路径
        priorPath = LOCK_PREFIX + waiters.get(index - 1);
        log.info("前一个节点路径:{}", priorPath);
        return false;
    }

    /**
     * 获取节点的编号
     * @param path
     * @return
     */
    private String getShortPath(String path) {
        int index = path.lastIndexOf(SEQ);
        if (index >= 0) {
            index += SEQ.length();
            return index <= path.length() ? path.substring(index) : "";
        }
        return null;
    }

    /**
     * 判断自己是否为最小序号
     * @return true-是 false-否
     */
    private boolean checkLock(List<String> waiters) {
        // 如果当前节点是最小编号, 成功获取锁
        if (lockedShortPath.equals(waiters.get(0))) {
            log.info("成功获取分布式锁, 节点路径为:{}", lockedShortPath);
            return true;
        }
        return false;
    }

    /**
     * 获取加锁的编号
     * @return 只返回编号, 不包含前缀
     */
    private List<String> getWaiters() {
        List<String> waiters = new ArrayList<>();
        try {
            List<String> strings = client.getChildren().forPath(ZK_PATH);
            log.info("子节点列表 = {}", strings);
            strings.forEach(item -> waiters.add(getShortPath(item)));
            // 按照编号, 升序排列
            Collections.sort(waiters);
            return waiters;
        } catch(Exception e) {
            log.error("获取临时节点失败", e);
        }
        return waiters;
    }

    /**
     * 监听前一个节点释放锁
     * @throws Exception
     */
    private void await() throws Exception {
        if (null == priorPath) {
            throw new Exception("priorPath error");
        }
        
        final CountDownLatch latch = new CountDownLatch(1);

        Watcher watcher = event -> {
            log.info("监听到节点删除, watchEvent={}", event);
            
            latch.countDown();
        };
        
        // 监听前一个节点
        client.getData().usingWatcher(watcher).forPath(priorPath);
        latch.await(WAIT_TIME, TimeUnit.SECONDS);
    }
}
