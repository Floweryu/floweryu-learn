package code.hot;

/**
 * @author Floweryu
 * @date 2024/2/28 20:00:26
 */
public class Hot238 {
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        // 先计算i左侧乘积
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            answer[i] = product;
            product *= nums[i];
        }
        // 再计算i右侧乘积，同时计算结果
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] *= product;
            product *= nums[i];
        }
        return answer;
    }
}
