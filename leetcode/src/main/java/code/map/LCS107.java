package code.map;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LCR 107. 01 矩阵
 * https://leetcode.cn/problems/2bCMpM/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/09/01
 */
public class LCS107 {

    /**
     * 定义四个方向
     */
    private int[][] direction = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        // 结果集
        int[][] ans = new int[row][col];
        // 标识该位置元素是否已经计算过
        boolean[][] visited = new boolean[row][col];
        // 广度优先遍历
        Queue<int []> queue = new LinkedList<>();
        // 将所有的0都进入队列
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            // 获取当前0的坐标
            int[] cell = queue.poll();
            int i = cell[0];
            int j = cell[1];
            // 遍历四个方向
            for (int[] derec : direction) {
                // 计算该方向位置
                int dstI = i + derec[0];
                int dstJ = j + derec[1];
                // 由于每次都只计算1，所以对每个0来说，如果可达的节点不是0，则该节点最近的0距离j就是1
                if (dstI >= 0 && dstI < row && dstJ >= 0 && dstJ < col && !visited[dstI][dstJ]) {
                    ans[dstI][dstJ] = ans[i][j] + 1;
                    // 将计算过的节点标记，并加入队列
                    // 下次从该节点出发寻找下一个未标记的非0节点。有点类似动态规划
                    visited[dstI][dstJ] = true;
                    queue.offer(new int[]{dstI, dstJ});
                }
            }
        }
        return ans;
    }
}
