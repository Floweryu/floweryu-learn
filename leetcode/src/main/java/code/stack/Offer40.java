package code.stack;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 * https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/17
 */
public class Offer40 {
    public int[] getLeastNumbers(int[] arr, int k) {
        // 默认创建小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.offer(arr[i]);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}
