package code.dp;

/**
 * @author zhangjunfeng
 * @date 2023/1/16 15:22
 */
public class Dp1716 {
    public static int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // 存当前位置时最大值
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // [i-1]位置和[i-2]位置+nums[i]比较, 如果[i-1]处大, 则平移这个值到[i]
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
    
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 4, 5, 3, 1, 1, 3};
        massage(arr);
    }
}
