package code.dynamic;

/**
 * @author Floweryu
 * @date 2022/12/7 23:00
 */
public class LeetCode1646 {
    public static int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        int max = -1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[(i - 1) / 2] + nums[(i - 1) / 2 + 1];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }
    
    public static void main(String[] args) {
        int maximumGenerated = getMaximumGenerated(7);
        System.out.println(maximumGenerated);
    }
}
