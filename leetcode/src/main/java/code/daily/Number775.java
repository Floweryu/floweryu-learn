package code.daily;

/**
 * @author zhangjunfeng
 * @date 2022/11/16 19:35
 */
public class Number775 {
    public static boolean isIdealPermutation(int[] nums) {
        int max = nums[0];
        for (int i = 2; i < nums.length; i++) {
            
            if (nums[i] < max) {
                return false;
            }
            max = Math.max(max, nums[i - 1]);
        }
        return true;
    }
    
    
    public static void main(String[] args) {
        int[] arr = {1,2,0};
        boolean idealPermutation = isIdealPermutation(arr);
        System.out.println(idealPermutation);
    }
}
