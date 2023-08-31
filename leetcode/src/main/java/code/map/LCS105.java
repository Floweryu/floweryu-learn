package code.map;

/**
 * LCR 105. 岛屿的最大面积
 * https://leetcode.cn/problems/ZL6zAn/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/08/30
 */
public class LCS105 {

    private int ans = 0;
    private int count = 0;
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                count = 0;
                backTrack(grid, i, j);
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    private void backTrack(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] != 1) {
            return;
        }

        count++;
        grid[i][j] = 2;
        backTrack(grid, i + 1, j);
        backTrack(grid, i - 1, j);
        backTrack(grid, i, j + 1);
        backTrack(grid, i, j - 1);
    }

    public static void main(String[] args) {
        LCS105 lcs105 = new LCS105();
        int ans = lcs105.maxAreaOfIsland(new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}});
        System.out.println(ans);
    }
}
