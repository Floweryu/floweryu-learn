package code.stack;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 41. 数据流中的中位数
 * https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/17
 */
public class MedianFinder {

    PriorityQueue<Integer> pq;
    public MedianFinder() {
        pq = new PriorityQueue<>();
    }

    public void addNum(int num) {
        pq.offer(num);
    }

    public double findMedian() {
        int size = pq.size();
        if (size % 2 == 0) {
            int index = size / 2;
            while (index-- > 1) {
                pq.poll();
            }
            return (pq.poll() + pq.poll()) / 2;
        } else {
            int index = size / 2 + 1;
            while (index-- > 1) {
                pq.poll();
            }
            return pq.poll();
        }
    }
}
