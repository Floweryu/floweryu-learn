package code.hot;

import java.util.*;

/**
 * 207. 课程表
 * @author Floweryu
 * @date 2024/3/7 10:43:27
 */
public class Hot207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] in = new int[numCourses];
        for (int[] arr : prerequisites) {
            // 统计节点的下一个节点
            List<Integer> list = map.getOrDefault(arr[1], new ArrayList());
            list.add(arr[0]);
            map.put(arr[1], list);
            // 计算节点的入度
            in[arr[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < in.length; i++) {
            // 将入度为0的节点入队
            if (in[i] == 0) {
                queue.add(i);
            }
        }

        // 统计该节点是否被用过
        Set<Integer> used = new HashSet<>();
        while (!queue.isEmpty()) {
            int val = queue.poll();
            // 如果该节点已经用过，但此时再次遇到，则说明有环
            if (used.contains(val)) {
                return false;
            }
            used.add(val);
            // 得到当前节点的下一个连接节点
            List<Integer> list = map.get(val);
            if (list != null && !list.isEmpty()) {
                for (Integer integer : list) {
                    // 将下一个节点入度减一
                    in[integer]--;
                    if (in[integer] == 0) {
                        queue.add(integer);
                    }
                }
            }

        }
        // 如果所有节点都已经使用，说明没有环
        return used.size() == numCourses;
    }
}
