package code.map;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LCR 106. 判断二分图
 * https://leetcode.cn/problems/vEAB3K/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/08/31
 */
public class LCS106 {

    private int colorA = 1;
    private int colorB = 2;
    private int unknow = 0;
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int[] color = new int[len];
        for (int i = 0; i < len; i++) {
            // 如果节点已经染色了直接跳过
            if (color[i] != unknow) {
                continue;
            }
            Queue<Integer> queue = new LinkedList<>();
            // 将当前节点染色
            queue.offer(i);
            color[i] = colorA;
            // 广度优先遍历
            while (!queue.isEmpty()) {
                Integer poll = queue.poll();
                // 计算下一个节点的颜色
                int nextColor = color[poll] == colorA ? colorB : colorA;
                // 遍历相邻节点
                for (int next : graph[poll]) {
                    // 如果相邻节点颜色一样，则说明不是二分图
                    if (color[next] == color[poll]) {
                        return false;
                    } else if (color[next] == unknow) {
                        // 如果相邻节点没染色，则对其进行染色
                        color[next] = nextColor;
                        // 添加到队列中，下次判断该节点相邻节点颜色是否一致，一致则说明不能构成二分图
                        queue.offer(next);
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LCS106 lcs106 = new LCS106();
        lcs106.isBipartite(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}});
    }
}
