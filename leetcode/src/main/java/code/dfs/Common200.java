package code.dfs;

/**
 * 200. 岛屿数量
 * https://leetcode.cn/problems/number-of-islands/
 * @author: zhangjunfeng
 * @createTime: 2023/05/25
 */
public class Common200 {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 从陆地开始递归，
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    // 因为在递归过程中已经访问过的点标记为2，所以每次递归都会把岛屿的点都变成2，这里使用统计剩下1的数量即可
                    count++;
                }
            }
        }
        return count;
    }

    // 网格递归遍历基本模板
    public void dfs(char[][] grid, int i, int j) {
        // 如果超出网格边界，直接返回
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        // 如果不是陆地, 结束递归
        if (grid[i][j] != '1') {
            return;
        }
        // 将递归后的陆地标记一下，不然会循环递归
        grid[i][j] = '2';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
