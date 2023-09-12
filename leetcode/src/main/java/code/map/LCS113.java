package code.map;

import java.util.*;

/**
 * LCR 113. 课程表 II
 * https://leetcode.cn/problems/QA2IGt/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/09/06
 */
public class LCS113 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] in = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] arr : prerequisites) {
            // 映射节点关系
            List<Integer> list = map.getOrDefault(arr[1], new ArrayList<>());
            list.add(arr[0]);
            map.put(arr[1], list);
            // 计算节点入度
            in[arr[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 入度为0的节点进队列
            if (in[i] == 0) {
                queue.offer(i);
            }
        }
        // 记录答案
        List<Integer> ans = new ArrayList<>();
        // 记录已经选取过的入度为0的节点
        Set<Integer> used = new HashSet<>();
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            // 如果该节点已经被记录了，但后续又遍历到了，说明图里面有环
            if (used.contains(node)) {
                return new int[0];
            }
            // 该节点已经被选取
            used.add(node);
            // 记录答案
            ans.add(node);
            // 获取与该节点关联下一个节点
            List<Integer> nextList = map.get(node);
            if (nextList != null && !nextList.isEmpty()) {
                for (Integer next : nextList) {
                    // 先将下一个节点入度减1
                    in[next]--;
                    // 如果减1后入度为0，可以作为下一个被选取的节点
                    if (in[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

        }
        // 如果答案重包含所有节点
        return ans.size() == numCourses ? ans.stream().mapToInt(i -> i).toArray() : new int[0];
    }

    public static void main(String[] args) {
        LCS113 lcs = new LCS113();
        int[][] arr = {{1, 0}};
        lcs.findOrder(2, arr);
    }
}
