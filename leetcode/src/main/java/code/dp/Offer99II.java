package code.dp;

/**
 * 剑指 Offer II 099. 最小路径之和
 * https://leetcode.cn/problems/0i0mDW/description/
 * @author: zhangjunfeng
 * @createTime: 2023/05/29
 */
public class Offer99II {

    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        // 第一行特殊处理，直接在前一列基础上累加
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // 第一列特殊处理，直接在前一行基础上累加
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 其他情况去上下相邻的最小值累加
        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }


    public int minPathSum2(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col];
        dp[0] = grid[0][0];
        // 第一行特殊处理，直接在前一列基础上累加
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        // 其他情况去上下相邻的最小值累加
        for (int i = 1; i < row; i ++) {
            // 遍历到第0列时，其实原二维第[i-1][0]位置记录已经无效了，可以压缩到dp[0]转为1维数组
            /**
             * 例如：
             *  1 3 1
             *  1 5 1
             *  4 2 1
             *  i = 0时, dp为：1 3 1
             *  i = 1时, dp[0]为 1+1=2，此时dp数组为：2 3 1
             *  接上一步j = 1时，dp[1] = min(dp[0], dp[1]) + grid[1][1]
             *  实则还是dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
             *  只是dp[0][0]处元素在i=1和j=1时没用，将dp[1][0]滚动到dp[0][0]而已，就优化成一维数组
             */
            dp[0] += grid[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }
        return dp[col - 1];
    }

}
