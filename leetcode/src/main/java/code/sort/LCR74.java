package code.sort;

import java.util.*;

/**
 * LCR 074. 合并区间
 * https://leetcode.cn/problems/SsGoHC/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @date: 2023/12/07
 */
public class LCR74 {

    public int[][] merge(int[][] intervals) {
        // 根据第一位从小到大排列
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            // 获取已经完成合并的最后一个数组，因为该数组有可能会和解下来的数组还能再合并
            int[] pre = res.get(res.size() - 1);
            if (cur[0] <= pre[1]) {
                // 直接修改结果集最后一个数组表示的范围
                pre[1] = Math.max(cur[1], pre[1]);
            } else {
                // 如果不能合并，则直接添加到结果集
                res.add(cur);
            }
        }
        return res.toArray(int[][]::new);
    }

    public static void main(String[] args) {
        int[][] arr = {
                {2, 3},
                {2, 2},
                {3, 3},
                {1, 3},
                {5, 7},
                {2, 2},
                {4, 6}
        };
        LCR74 lcr74 = new LCR74();
        int[][] merge = lcr74.merge(arr);
        for (int[] row : merge) {
            System.out.println(Arrays.toString(row));
        }
    }
}
