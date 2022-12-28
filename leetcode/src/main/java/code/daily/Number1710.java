package code.daily;

import java.util.Arrays;

/**
 * @author zhangjunfeng
 * @date 2022/11/15 19:23
 */
public class Number1710 {
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        // 贪心算法, 先按最大单元数排序
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        
        int res = 0;
        for (int[] boxType : boxTypes) {
            // 背包大于箱子数, 直接全部添加到背包
            if (truckSize >= boxType[0]) {
                res += boxType[1] * boxType[0];
                truckSize -= boxType[0];
            } else {
                // 背包小于箱子数, 用背包剩余空间x单元
                res += truckSize * boxType[1];
                break;
            }
        }
        
        return res;
    }
    
    
    public static void main(String[] args) {
        int[][] boxTypes = {{5,10},{2,5},{4,7}, {3,9}};
        int truckSize = 10;
    
        int res = maximumUnits(boxTypes, truckSize);
        System.out.println(res);
    }
}
