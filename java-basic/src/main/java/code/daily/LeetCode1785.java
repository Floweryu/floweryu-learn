package code.daily;

/**
 * @author Floweryu
 * @date 2022/12/16 11:57
 */
public class LeetCode1785 {
    public static int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int a : nums) {
            sum += a;
        }
        long gap = Math.abs(goal - sum);
    
        return (int) (gap / limit + (gap % limit == 0 ? 0 : 1));
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{1,-10,9,1};
        int val = minElements(nums, 100, 0);
        System.out.println(val);
    }
}
