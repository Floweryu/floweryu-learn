package code.hot;

/**
 * 240. 搜索二维矩阵 II
 * @author Floweryu
 * @date 2024/2/29 16:38:29
 */
public class Hot240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;
        int top = 0, left = 0;
        while (top <= bottom && left <= right) {
            if (target < matrix[bottom][left]) {
                bottom--;
            } else if (target > matrix[bottom][left]) {
                left++;
            } else {
                return true;
            }

            if (target < matrix[top][right]) {
                right--;
            } else if (target > matrix[top][right]) {
                top++;
            } else {
                return true;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        Hot240 hot240 = new Hot240();
        int[][] matrix = new int[][]{
                {1},
                {3},
                {5}
        };
        hot240.searchMatrix(matrix, 1);
    }
}
