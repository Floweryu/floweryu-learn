package code.map;

/**
 * LCR 116. 省份数量
 * https://leetcode.cn/problems/bLyHh0/description/?envType=study-plan-v2&envId=coding-interviews-special
 * 可转化为求图的连通分量
 * 连通分量：图中最大连通子图的个数
 * @author: zhangjunfeng
 * @date: 2023/10/09
 */
public class LCS116 {
    public int findCircleNum(int[][] isConnected) {
        // 标记定点是否被遍历
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(isConnected, i, visited);
                // 每次递归结束即一个连通分量
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] isConnected, int i, boolean[] visited) {
        // 针对节点i, 递归判断是否有其他节点和i相连
        for (int j = 0; j < isConnected[i].length; j++) {
            if (!visited[j] && isConnected[i][j] == 1) {
                visited[j] = true;
                dfs(isConnected, j, visited);
            }
        }
    }
}
