package code.dp;

import java.util.Arrays;

/**
 * 剑指 Offer II 103. 最少的硬币数目
 * https://leetcode.cn/problems/gaM7Ch/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/06/29
 */
public class Offer103II {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        // dp[i]表示装满容量i需要的最少硬币数目
        int[] dp = new int[amount + 1];
        // 容量最大为amount，假设硬币有1，则需要的最多的硬币数据为amount，这里初始化数组为最大值（表示每个容量默认都不可能装满）
        Arrays.fill(dp, amount + 1);
        // 容量为0不需要装
        dp[0] = 0;
        // 针对每个容量，使用不同币种去填充，然后不同币种取最小值
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                // 如果该coin小于本次要装的背包容量
                if (coin <= i) {
                    // 因为有多个币种，所以取最小的那个币种的方案
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        Offer103II offer103II = new Offer103II();
        int i = offer103II.coinChange(coins, amount);
        System.out.println(i);
    }
}
