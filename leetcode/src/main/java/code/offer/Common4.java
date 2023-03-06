package code.offer;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/?favorite=xb9nqhhg
 * @author zhangjunfeng
 * @date 2023/3/6 19:34
 */
public class Common4 {
    /**
     * 基础解法: 遍历每一行二分查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int[] ints : matrix) {
            int l = 0, r = ints.length - 1;
            while (l <= r) {
                int index = (l + r) / 2;
                if (ints[index] == target) {
                    return true;
                } else if (ints[index] < target) {
                    l++;
                } else if (ints[index] > target) {
                    r--;
                }
            }
        }
        return false;
    }
    
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        // 从左下角元素开始
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j <= matrix[0].length - 1) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            }
        }
        return false;
    }
}
