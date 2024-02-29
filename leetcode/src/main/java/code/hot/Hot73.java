package code.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 73. 矩阵置零
 * @author Floweryu
 * @date 2024/2/29 10:49:20
 */
public class Hot73 {
    public void setZeroes(int[][] matrix) {
        List<int[]> indexList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    indexList.add(new int[]{i, j});
                }
            }
        }

        for (int[] arr : indexList) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][arr[1]] = 0;
            }
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[arr[0]][i] = 0;
            }
        }

    }

}
