package code.stack;

import java.util.PriorityQueue;

/**
 * 剑指 Offer II 076. 数组中的第 k 大的数字
 * https://leetcode.cn/problems/xx4gT2/
 * @author: zhangjunfeng
 * @createTime: 2023/05/17
 */
public class OfferII76 {
    public int findKthLargest(int[] nums, int k) {
        // 建立大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        for (int num : nums) {
            pq.offer(num);
        }
        // 把前k大剔除
        while (k-- > 1) {
            pq.poll();
        }
        // 返回第k大
        return pq.poll();
    }
}
