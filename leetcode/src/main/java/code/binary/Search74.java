package code.binary;

/**
 * 74. 搜索二维矩阵 同剑指offer04
 * https://leetcode.cn/problems/search-a-2d-matrix/description/
 * 以矩阵左下角位置tmp为观察点: 小于tmp的元素都在tmp所在行之上, 大于tmp的元素都在tmp所在列向右
 * 所以, 每次比较tmp和target的值, 然后舍弃行列即可, 规则如下：
 * 1. 若target > tmp, 则说明tmp该列向左的元素都小于target, 所以舍弃该列，即j++
 * 2. 若target < tmp, 则说明target在tmp所在行之上，所以舍弃该行，即i--
 * @author zhangjunfeng
 * @date 2023/3/16 13:06
 */
public class Search74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j <= matrix[0].length - 1) {
            if (target > matrix[i][j]) {
                j++;
            } else if (target < matrix[i][j]) {
                i--;
            } else {
                return true;
            }
        }
        return false;
    }
}
