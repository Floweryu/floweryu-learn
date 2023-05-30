package code.sort;

/**
 * 剑指 Offer II 075. 数组相对排序
 * https://leetcode.cn/problems/0H97ZC/?envType=study-plan-v2&envId=coding-interviews-special
 * 计数排序
 * @author: zhangjunfeng
 * @createTime: 2023/05/30
 */
public class Offer75II {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 为节省空间，可以先算出数组中最大值
        int max = 0;
        for (int v : arr1) {
            max = Math.max(v, max);
        }
        // 初始化max+1空间数组
        int[] count = new int[max + 1];
        // 统计arr1中每个数字出现次数
        for (int v : arr1) {
            count[v]++;
        }
        int index = 0;
        int[] res = new int[arr1.length];
        // 此时以arr2数组中的顺序为主（计数排序是以统计次数的数组下标来遍历，因为下标是有序的）
        for (int v : arr2) {
            for (int i = 0; i < count[v]; i++) {
                res[index++] = v;
            }
            // 每个数填完后次数归0
            count[v] = 0;
        }

        // 最后遍历计数数组，按照下标遍历，因为这样arr1中剩下数组就会按照有序排列
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < count[i]; j++) {
                res[index++] = i;
            }
        }
        return res;
    }
}
