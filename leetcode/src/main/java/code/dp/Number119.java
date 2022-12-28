package code.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangjunfeng
 * @date 2022/11/14 19:22
 */
public class Number119 {
    /**
     * 1,0,0,0,0
     * 1,1,0,0,0
     * 1,2,1,0,0
     * 1,3,3,1,0
     * 1,4,6,4,1
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return List.of(1);
        }
        if (rowIndex == 1) {
            return List.of(1, 1);
        }
        int[][] array = new int[rowIndex + 1][rowIndex + 1];
        array[0][0] = 1; 
        array[1][0] = 1;
        array[1][1] = 1;
        
        for (int i = 2; i <= rowIndex; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                }
                
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            res.add(array[rowIndex][i]);
        }
        return res;
    }
    
    public static List<Integer> getRow2(int rowIndex) {
        Integer[] array = new Integer[rowIndex + 1];
        Arrays.fill(array, 1);
        /**
         * 0, 1时不需要考虑，所以从2开始计算
         * 第二层循环为什么从i - 1?因为正向遍历会覆盖j - 1的值, j依赖于j - 1处的值, i-1处值为1, 不需要更新
         * i: 表示第几层; j: 表示该层的下标
         */
        for (int i = 2; i < rowIndex + 1; i++) {
            for (int j = i - 1; j > 0; j--) {
                array[j] = array[j - 1] + array[j];
            }
        }
        
        return Arrays.asList(array);
    }
    
    
    public static void main(String[] args) {
        List<Integer> row = getRow2(4);
        System.out.println(row);
    }
}
