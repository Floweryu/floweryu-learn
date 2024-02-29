package code.hot;

/**
 * 41. 缺失的第一个正数
 * 思路：缺失的第一个正整数范围在[1, nums.length + 1]之间。证明如下：
 * 假设nums里面是从1开始递增的正整数，则缺失的第一个正整数就是nums.length+1
 * 假设nums里面并不是完全的从1开始递增，则缺失的第一个正整数是[1, nums.length]
 * @author Floweryu
 * @date 2024/2/28 20:27:44
 */
public class Hot41 {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 先把负数都标记为一个很大的值，不在结果范围内就行
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }

        for (int i = 0; i < len; i++) {
            // 将小于等于len的正数作为下标的值变为负数标记
            int abs = Math.abs(nums[i]);
            if (abs <= len) {
                nums[abs - 1] = -Math.abs(nums[abs - 1]);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return len + 1;
    }
}
