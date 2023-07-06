package code.dp;

/**
 * 剑指 Offer II 104. 排列的数目
 * https://leetcode.cn/problems/D0F0SV/?envType=study-plan-v2&envId=coding-interviews-special
 * 思路：
 * 用 dp[x] 表示选取的元素之和等于 x 的方案数，目标是求 dp[target]。
 * @author: zhangjunfeng
 * @createTime: 2023/06/30
 */
public class Offer104II {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {

    }
}
