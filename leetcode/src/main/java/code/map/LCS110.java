package code.map;

import java.util.*;

/**
 * LCR 110. 所有可能的路径
 * https://leetcode.cn/problems/bP4bmD/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/09/05
 */
public class LCS110 {

    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        backTrack(graph, 0, list);
        return ans;
    }

    public void backTrack(int[][] graph, int index, List<Integer> cur) {
        // 遇到n-1节点时找到一个答案
        if (index == graph.length - 1) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < graph[index].length; i++) {
            cur.add(graph[index][i]);
            // 寻找下一个节点
            backTrack(graph, graph[index][i], cur);
            cur.remove(cur.size() - 1);
        }
    }
}
