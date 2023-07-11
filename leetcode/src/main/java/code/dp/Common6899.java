package code.dp;

import java.util.Arrays;

/**
 * 2770. 达到末尾下标所需的最大跳跃次数
 * https://leetcode.cn/problems/maximum-number-of-jumps-to-reach-the-last-index/description/
 * 示例: nums = [1,3,6,4,1,2], target = 2
 * 思路：定义dp[i]表示跳跃到i位置时最大跳跃次数
 * 对于位置nums[i]来说，必然从[0, i-1]中某个位置跳转得来，即dp[i] = dp[j] + 1
 * @author: zhangjunfeng
 * @createTime: 2023/07/10
 */
public class Common6899 {
    public int maximumJumps(int[] nums, int target) {
        int len = nums.length;
        int[] dp = new int[len];
        // 初始值每个位置设为不可达，即-1
        Arrays.fill(dp, -1);
        // 位置0时最大跳跃数为0
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 如果符合条件，并且j位置可达，说明i位置可以从j跳跃而来 dp[i] = dp[j] + 1
                // 由于要取最大跳跃数，所以要取最大值
                if (Math.abs(nums[i] - nums[j]) <= target && dp[j] != -1) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        Common6899 common6899 = new Common6899();
        int i = common6899.maximumJumps(new int[]{1, 3, 6, 4, 1, 2}, 2);
        System.out.println(i);
    }
}
