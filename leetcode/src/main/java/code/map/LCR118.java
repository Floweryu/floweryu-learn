package code.map;

/**
 * LCR 118. 冗余连接
 * https://leetcode.cn/problems/7LpjUW/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @date: 2023/10/17
 */
public class LCR118 {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // 下标表示子节点，值表示父结点
        int[] parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            // 如果该边两个节点不在集合同一中，则说明可以选择这条边
            if (find(parents, node1) != find(parents, node2)) {
                union(parents, node1, node2);
            } else {
                // 在同一集合中，如果还把边加入就会形成环
                // 又因为边是有序的，所以此时直接返回
                return edge;
            }
        }
        return new int[0];
    }

    /**
     * 合并两个节点的父结点
     * @param parents
     * @param node1
     * @param node2
     */
    private void union(int[] parents, int node1, int node2) {
        parents[find(parents, node1)] = find(parents, node2);
    }

    /**
     * 查询当前节点的根结点
     * @param parents 父结点集合
     * @param index 子节点
     * @return 父结点
     */
    private int find(int[] parents, int index) {
        // 不相等说明已经合并过
        if (parents[index] != index) {
            parents[index] = find(parents, parents[index]);
        }
        return parents[index];
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2},{1,3},{2,3}};
        LCR118 lcr118 = new LCR118();
        int[] redundantConnection = lcr118.findRedundantConnection(arr);
        System.out.println(redundantConnection);
    }
}
