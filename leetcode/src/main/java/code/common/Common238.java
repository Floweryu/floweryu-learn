package code.common;

/**
 * 238. 除自身以外数组的乘积
 * https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-interview-150
 * @author: zhangjunfeng
 * @date: 2024/01/22
 */
public class Common238 {
    public int[] productExceptSelf(int[] nums) {
        int[] leftAns = new int[nums.length];
        int[] rightAns = new int[nums.length];


        int tmp = 1;
        // 计算i左侧的乘积
        for (int i = 0; i < nums.length; i++) {
            leftAns[i] = tmp;
            tmp *= nums[i];
        }

        tmp = 1;
        // 计算i右侧的乘积
        for (int i = nums.length - 1; i >= 0; i--) {
            rightAns[i] = tmp;
            tmp *= nums[i];
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = rightAns[i] * leftAns[i];
        }

        return res;
    }

    public int[] productExceptSelf2(int[] nums) {
        int[] answer = new int[nums.length];
        int tmp = 1;
        // 计算i左侧的乘积
        for (int i = 0; i < nums.length; i++) {
            answer[i] = tmp;
            tmp *= nums[i];
        }

        tmp = 1;
        // 计算i右侧的乘积
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] *= tmp;
            tmp *= nums[i];
        }
        return answer;
    }
}
