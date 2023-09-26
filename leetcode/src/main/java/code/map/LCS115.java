package code.map;

import java.util.*;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/09/12
 */
public class LCS115 {
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int len = nums.length + 1;
        int[] in = new int[len];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] arr : sequences) {
            for (int i = 0; i < arr.length - 1; i++) {
                List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
                list.add(arr[i + 1]);
                map.put(arr[i], list);
                in[arr[i + 1]]++;
            }

        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 如果队列中有两个入度不为0的节点，则说明有两个不同的拓扑排序，即没有最短序列
            if (queue.size() != 1) {
                return false;
            }
            Integer node = queue.poll();
            List<Integer> nextList = map.get(node);
            if (nextList != null) {
                for (Integer next : nextList) {
                    if (in[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return true;
    }
}
