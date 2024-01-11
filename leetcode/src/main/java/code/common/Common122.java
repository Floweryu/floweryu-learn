package code.common;

/**
 * 122. 买卖股票的最佳时机 II
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * @author: zhangjunfeng
 * @date: 2024/01/11
 */
public class Common122 {
    public int maxProfit(int[] prices) {
        int len =  prices.length;

        // dp[i][0] 不持有股票（卖出股票）
        // dp[i][1] 持有股票
        // 状态转移：0 ——> 1 ——> 0 ——> 1 ...
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 不买i股票转移方程 = max(不买i-1股票， 买了i-1股票但在i时卖出)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 买股票转移方程 = max(买i-1股票，不买i-1但买i股票)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 最后肯定是不买股票（或者卖出股票）的钱比买股票的钱多，所以状态取0
        return dp[len - 1][0];
    }
}
