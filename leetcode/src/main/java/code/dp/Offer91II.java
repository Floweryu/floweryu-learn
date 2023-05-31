package code.dp;

import java.util.Arrays;

/**
 * 剑指 Offer II 091. 粉刷房子
 * https://leetcode.cn/problems/JEj789/?envType=study-plan-v2&envId=coding-interviews-special
 * 17 2 17
 * 16 16 5
 * 14 3 19
 * @author: zhangjunfeng
 * @createTime: 2023/05/31
 */
public class Offer91II {
    public int minCost(int[][] costs) {
        int row = costs.length;
        int[][] dp = new int[row][3];
        System.arraycopy(costs[0], 0, dp[0], 0, 3);
        for (int i = 1; i < row; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];

        }
        return Arrays.stream(dp[row - 1]).min().getAsInt();
    }

    public int minCostV2(int[][] costs) {
        int row = costs.length;
        // 二维dp数组简化为一维
        // 因为dp数组只需要记录前面一间房最小花费即可
        // dp数组含义：刷到当前房间时每种颜色的最小花费
        int[] dp = new int[3];
        System.arraycopy(costs[0], 0, dp, 0, 3);
        // 从第2行开始计算，因为第1间房最小花费不用计算
        for (int i = 1; i < row; i++) {
            // 因为dp数组在为每一间房赋值过程中不能变化，所以需要有一个临时数组
            int[] tmp = new int[3];
            for (int j = 0; j < 3; j++) {
                // 这里巧妙的使用(j + 1) % 3和(j + 2) % 3来使相邻房间颜色不重复
                // 只需要去前面一间房最小费用当前房间颜色花费保存即可
                tmp[j] = Math.min(dp[(j + 1) % 3], dp[(j + 2) % 3]) + costs[i][j];
            }
            dp = tmp;
        }
        return Arrays.stream(dp).min().getAsInt();
    }
}
