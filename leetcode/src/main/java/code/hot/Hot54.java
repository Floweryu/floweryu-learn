package code.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * @author Floweryu
 * @date 2024/2/29 11:52:45
 */
public class Hot54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = matrix[0].length - 1, up = 0, down = matrix.length - 1;
        while (up <= down && left <= right) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[up][i]);
            }
            for (int i = up + 1; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            if (up < down && left < right) {
                for (int i = right - 1; i >= left; i--) {
                    ans.add(matrix[down][i]);
                }
                for (int i = down - 1; i >= up + 1; i--) {
                    ans.add(matrix[i][left]);
                }

            }
            up++;
            right--;
            down--;
            left++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Hot54 hot54 = new Hot54();
        int[][] matrix = new int[][]{{6,9,7}};
        hot54.spiralOrder(matrix);
    }
}
