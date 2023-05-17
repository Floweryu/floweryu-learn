package code.stack;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/16
 */
public class Offer59I {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 创建一个大顶堆，规则如下：
        // 如果数值相等，则比较下标，下标大的交换位置
        // 如果数值不等，则比较数值，数值大的交换位置
        // int[0]表示数值，int[1]表示下标
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]);
        int length = nums.length;
        // 先将前k个元素放入大顶堆
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
        }
        // 初始化返回数组
        int[] res = new int[length - k + 1];
        // 将第一个窗口k的最大值放入返回窗口
        res[0] = priorityQueue.peek()[0];
        // 从第k个元素开始遍历，获取后面窗口最大值
        for (int i = k; i < length; i++) {
            // 把新元素加入滑动窗口, 构造新的大顶堆
            // 这里可能会遇到值相等但下标不同的元素，所以在初始化大顶堆时比较器要筛选下标大的那个元素
            // 因为滑动窗口向后移，新加入元素下标肯定比之前大
            priorityQueue.offer(new int[]{nums[i], i});
            // 先把大顶堆中下标小于等于i-k的清除，因为这部分元素不再滑动窗口内部
            while (priorityQueue.peek()[1] <= i - k) {
                priorityQueue.poll();
            }
            // 接下来大顶堆里面元素都是滑动窗口内部元素, 获取最大值即可
            res[i - k + 1] = priorityQueue.peek()[0];
        }
        return res;
    }
}
