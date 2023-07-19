package competition;

/**
 * @author Floweryu
 * @date 2023/7/9 10:56
 */
public class Solution {
    public int maximumJumps(int[] nums, int target) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) <= target) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[nums.length - 1] == 0 ? -1 : dp[nums.length - 1];
    }

    public int maximumJumps2(int[] nums, int target) {
        int ans = 0, index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (i > index && Math.abs(nums[i] - nums[index]) <= target) {
                ans++;
                index = i;
                System.out.println(index);
            }
        }
        return index == nums.length - 1 ? ans : -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1, 0, 2};
        int target = 1;
        solution.maximumJumps2(arr, target);
    }
}
