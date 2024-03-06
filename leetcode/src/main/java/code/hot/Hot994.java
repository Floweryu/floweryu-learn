package code.hot;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Floweryu
 * @date 2024/3/6 20:38:13
 */
public class Hot994 {
    public int orangesRotting(int[][] grid) {
        int[] ir = {-1, 0, 1, 0};
        int[] jr = {0, -1, 0, 1};
        int flash = 0, row = grid.length, col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 将腐烂的橘子入队
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    // 统计新鲜橘子的数量
                    flash++;
                }

            }
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int r = 0; r < 4; r++) {
                    int m = poll[0] + ir[0];
                    int n = poll[1] + jr[r];
                    if ((m >= 0 && m < row) && (n >= 0 && n < col)
                            && grid[m][n] == 1) {
                        // 将感染橘子周围好的橘子染色
                        grid[m][n] = 2;
                        // 新鲜橘子减一
                        flash--;
                        // 将新感染的橘子入队
                        queue.add(new int[]{m, n});
                    }
                }
            }
        }
        return flash != 0 ? -1 : ans;
    }
}
