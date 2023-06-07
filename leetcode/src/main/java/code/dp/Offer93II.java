package code.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 093. 最长斐波那契数列
 * https://leetcode.cn/problems/Q91FMA/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/06/05
 */
public class Offer93II {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        // 由于arr是递增有序的，所以判断下标即可判断arr中是否有该值
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(arr[i], i);
        }
        int ans = 0;
        // 遍历arr数组，以每个arr[i]作为第3个元素
        for (int i = 0; i < n; i++) {
            // 遍历arr[0, i)的元素，作为第2个元素
            // 这里为了少遍历，增加了一个条件arr[j] * 2 > arr[i]
            // 原因：假设第一个元素为arr[index], 则有arr[index]+arr[j]=arr[i]，同时index<j<i并且arr[index]<arr[j]<arr[i]
            // 所以欲满足arr[index]+arr[j]=arr[i]，必有arr[j]+arr[j]>arr[i]，这样可以省去遍历很多元素
            for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                int index = indexMap.getOrDefault(arr[i] - arr[j], -1);
                // 如果存在第一个值，则说明找到一个序列index, j, i可以组成斐波那契数列
                if (index >= 0) {
                    // 如果index, j之前就是斐波那数列，则第i个元素也满足就相当于在之前数列上延续，直接+1
                    // 否则找到一个序列就是长度为3，比如一开始的1,2,3
                    dp[j][i] = Math.max(dp[index][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 6};
        Offer93II offer93II = new Offer93II();
        int i = offer93II.lenLongestFibSubseq(arr);
        System.out.println(i);
    }
}
