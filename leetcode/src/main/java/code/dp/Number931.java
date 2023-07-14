package code.dp;

import java.util.Arrays;

/**
 * 931. 下降路径最小和
 * https://leetcode.cn/problems/minimum-falling-path-sum/
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * [[2,1,3]
 * ,[6,5,4]
 * ,[7,8,9]]
 *
 * @author: zhangjunfeng
 * @createTime: 2023/07/13
 */
public class Number931 {
    public int minFallingPathSum(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i <= row; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        Arrays.fill(dp[0], 0);

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], j + 1 <= col ? dp[i - 1][j + 1] : Integer.MAX_VALUE))
                        + matrix[i - 1][j - 1];

            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= col; i++) {
            ans = Math.min(dp[row][i], ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {2,1,3},{6,5,4},{7,8,9}
        };
        Number931 number931 = new Number931();
        number931.minFallingPathSum(matrix);
    }
}
