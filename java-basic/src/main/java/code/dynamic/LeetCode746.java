package code.dynamic;

import java.util.Arrays;

/**
 * @author Floweryu
 * @date 2022/12/5 14:44
 */
public class LeetCode746 {
    public static int minCostClimbingStairs(int[] cost) {

        for (int i = 2; i < cost.length; i++) {
            cost[i] = Math.min(cost[i - 1], cost[i - 2]) + cost[i];
        }
        
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
    
    public static void main(String[] args) {
        int[] arr = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int res = minCostClimbingStairs(arr);
        System.out.println(res);
        System.out.println(Arrays.toString(arr));
    }
}
