package code.map;

import java.util.HashSet;
import java.util.Set;

/**
 * LCR 119. 最长连续序列
 * https://leetcode.cn/problems/WhsWhI/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @date: 2023/12/06
 */
public class LCR119 {

    public int longestConsecutive(int[] nums) {

        // 去重，例如：[0, 0, 1, 2, 3]中，0重复不算
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longest = 0;

        for (int num : num_set) {
            // 防止重复
            // 该算法从最小数开始计算连续数字最长长度，所以如果有值比当前num小1，说明不应该从该num计算
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentCount = 1;

                // 判断数组中有多少个数字在此num上递增
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentCount += 1;
                }

                // 取每次的最大值
                longest = Math.max(longest, currentCount);
            }
        }

        return longest;
    }
}
