package code.dp;

/**
 * 剑指 Offer II 089. 房屋偷盗
 * https://leetcode.cn/problems/Gu0c2T/?envType=study-plan-v2&envId=coding-interviews-special
 * 如果房间梳理大于两间，有两个选项：
 * 1. 偷窃第k个房屋，就不能偷第k-1个房屋，偷窃总额为前k-2个房屋最高总额与第k间房屋的金额之和
 * 2. 不偷窃第k个房屋，偷窃总金额为前k-1间房屋最高总金额
 * dp[i]表示前i间房屋能偷窃的最大金额，状态转移方程如下：
 * dp[i] = Max(dp[i - 2] + nums[i], dp[i - 1])
 * 边界条件：
 * dp[0] = nums[0] 只有一间房屋
 * dp[1] = max(nums[0], nums[1]) 只有两间房屋
 * 最后结果：dp[nums.length - 1]
 * @author: zhangjunfeng
 * @createTime: 2023/05/30
 */
public class Offer89II {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 对每个房间都有偷与不偷两种选择
            // 假如偷：则上一个房间被偷的只能是i-2，加上本间房金额为：dp[i-2]+nums[i]
            // 加入不偷：则上一次被偷的房间是i-1，当前房间由于不被偷，总价值还是dp[i-1]
            // 两则去最大值
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
