package code.dfs;

/**
 * 剑指 Offer II 112. 最长递增路径
 * https://leetcode.cn/problems/fpTFWP/description/
 * @author Floweryu
 * @date 2023/5/28 17:56
 */
public class Offer112II {

    // 四个方向
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows, cols;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        cols = matrix[0].length;
        // 记忆矩阵: 记录遍历当前点位时最大路径
        int[][] memo = new int[rows][cols];
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 遍历每个矩阵点位
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    public int dfs(int[][] matrix, int i, int j, int[][] memo) {
        // 如果当前点位已经遍历过，返回当前点位时路径长度
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        // 遍历到当前定位, 则路径+1
        // 这里先把当前这个点路长度算出来，最短就只包含当前点长度1，下面那个dfs中的+1不一定能走的到，不满足递增的话就不走那里了，这样当前点局部最长路径长度依旧要赋值为1
        memo[i][j]++;
        // 遍历四个方向
        for (int[] dir : dirs) {
            int newI = i + dir[0], newJ = j + dir[1];
            // 1. 判断是否越界
            // 2. 判断下一步遍历的点位值是否比当前值大(贪心)
            if (newI >= 0 && newI < rows && newJ >= 0 && newJ < cols && matrix[newI][newJ] > matrix[i][j]) {
                // 每次递归路径值+1, 最后获取最大值
                memo[i][j] = Math.max(memo[i][j], dfs(matrix, newI, newJ, memo) + 1);
            }
        }
        return memo[i][j];
    }
}
