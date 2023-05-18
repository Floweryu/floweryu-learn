package code.stack;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 41. 数据流中的中位数
 * https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/17
 */
public class MedianFinder {
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> big;

    public MedianFinder() {
        // 将排序后的数据流分为两部分
        // 小顶堆存储排序后大的那部分值, 这样小顶堆能直接获取大的那部分最小值
        // 大顶堆存储排序后小的那部分值, 这样大顶堆能直接获取小的那部分最大值
        small = new PriorityQueue<>();
        big = new PriorityQueue<>((o1, o2) -> (o2 - o1));
    }

    public void addNum(int num) {
        big.offer(num);
        small.offer(big.poll());
        if (big.size() + 1 < small.size()) {
            // 如果大顶堆元素比小顶堆的少两个，则把小顶堆元素poll出来一个放入大顶堆
            // 永远保证大顶堆和小顶堆元素最接近中位数
            big.offer(small.poll());
        }
    }

    public double findMedian() {
        // 说明是奇数
        if (small.size() > big.size()) return small.peek();
        // 偶数直接去平均值
        return (small.peek() + big.peek()) / 2.0;
    }
}
