package code.dp;

/**
 * 剑指 Offer II 101. 分割等和子集
 * https://leetcode.cn/problems/NUPfPr/description/?envType=study-plan-v2&envId=coding-interviews-special
 * 0-1背包问题
 * 题目可以转换为：从数组Nums中寻找元素能装满sum(nums)/2容量的背包
 * 前面有些细节条件可以去掉很多测试用例：
 * 1. 数组元素之和不能被2整除
 * 2. 数组中最大值比所有元素之和一半还大
 * @author: zhangjunfeng
 * @createTime: 2023/06/21
 */
public class Offer101II {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        // 数组元素相加和不能被2整除，说明不能分为两个相等的部分
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        // 如果最大值比总和一半还大，也不n呢个分为两个相等的部分
        if (maxNum > target) {
            return false;
        }

        // 定义数组，使用0-1背包来解决此问题
        // 问题转化为在数组中能否选取合适元素，使得和为target
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                // 当前容量状态取决于不装num和装num，不装num是dp[j]，装num是dp[j-num]
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }
}
