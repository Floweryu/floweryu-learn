package code.dp;

/**
 * 剑指 Offer II 098. 路径的数目
 * https://leetcode.cn/problems/2AoeFn/description/
 * 思路一: 二维数组dp
 * 默认第一行dp[0][j]=1，第一列dp[i][0]=1
 * 后面dp[i][j] = dp[i-1][j] + dp[i][j-1]
 * 思路二：一维数组dp
 * 由于每次状态转移只依赖于当前元素上侧和左侧，所以可以把左侧下沉到上侧里面，构造一维数组
 * 例如：m = 5, n = 3，二维dp数组如下：
 * 1    1   1   1   1
 * 1    2   3   4   5
 * 1    3   6   10  15
 * 当i=1,j=1时，dp[1][1]只依赖于dp[1][0]和dp[0][1]
 * 如果使用一维数组, dp方程变为：dp[i] = dp[i] + dp[i - 1]
 * 外循环遍历行，修改整行状态；内循环遍历列，修改每列状态
 * 当i=0时，遍历j后dp数组为：1    1   1   1   1
 * 当i=1时，遍历j后dp数组为：1    2   3   4   5
 * 当i=2时，遍历j后dp数组为：1    3   6   10  15
 * @author: zhangjunfeng
 * @createTime: 2023/06/14
 */
public class Offer98II {
    /**
     * 二维dp数组
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 第一行和第一列默认为1
                if (i == 0) {
                    dp[0][j] = 1;
                }
                if (j == 0) {
                    dp[i][0] = 1;
                }
                if (i != 0 && j != 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 一维dp数组
     */
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }


    public static void main(String[] args) {
        Offer98II offer98II = new Offer98II();
        int i = offer98II.uniquePaths(3, 5);
        System.out.println(i);
    }
}
