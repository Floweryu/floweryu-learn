package code.dp;

/**
 * 剑指 Offer II 102. 加减的目标值
 * https://leetcode.cn/problems/YaVDxD/description/
 * 问题可以转化为0-1背包问题：
 * 对数组[1,1,1,1,1]
 * 设添加+号的元素之和为pos，添加-号的元素之和为neg
 * 则有：pos + neg = sum, pos - neg = target
 * 组合方程得到：pos = (sum + target) / 2,  neg = (sum - target) / 2
 * 所以上面问题化为：给定一个背包neg，从数组中找到一些数字（每个数字可以选一次），使得选出的数字和刚好等于pos或者neg。
 * @author: zhangjunfeng
 * @createTime: 2023/06/27
 */
public class Offer102II {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 去掉特殊情况
        int diff = sum - target;
        if (sum < target || diff % 2 != 0) {
            return 0;
        }

        // 计算背包容量
        int neg = diff / 2;
        // 0-1 背包
        int[] dp = new int[neg + 1];
        // 背包容量为0时，可以选择不选任何数，所以有一种方案
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                // dp[j]表示容量为j时刚好装满的方案数
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }
}
