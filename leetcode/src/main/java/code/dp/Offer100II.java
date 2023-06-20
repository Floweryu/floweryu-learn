package code.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/IlPe0q/description/
 * 剑指 Offer II 100. 三角形中最小路径之和
 * @author: zhangjunfeng
 * @createTime: 2023/06/14
 * 三角形数组
 * 2    0   0   0
 * 3    4   0   0
 * 6    5   7   0
 * 4    1   8   3
 * 构造一个dp数组，长宽和上面一致
 * 设置dp[0][0] = t[0][0]，因为是起点
 * dp状态转移方程分下面几个情况：
 * 1. 如果是第一列，则dp[i][0] = dp[i - 1][0] + t[i][0]，因为第一列只能通过上一层来改变
 * 2. 如果是对角线，则dp[i][i] = dp[i-1][j-1] + t[i][i]，因为对角线状态的更改只能通过[i-1][j-1]处来改变
 * 3. 其他情况：dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + t[i][j];
 */
public class Offer100II {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    // 第一列特殊处理
                    dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
                } else {
                    // 其他情况取最小值
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
            // 对角线状态的更改只能通过[i-1][j-1]处来改变特殊处理
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        // 寻找最后一行最小值即可
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, dp[m - 1][j]);
        }
        return ans;
    }


    public static void main(String[] args) {
        Offer100II offer100II = new Offer100II();
        int[][] arr = new int[][]{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(List.of(2));
        triangle.add(List.of(3, 4));
        triangle.add(List.of(6, 5, 7));
        triangle.add(List.of(4, 1, 8, 3));
        offer100II.minimumTotal(triangle);
    }
}
