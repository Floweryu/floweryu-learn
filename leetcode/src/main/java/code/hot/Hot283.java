package code.hot;

/**
 * 283. 移动零
 */
public class Hot283 {
    public void moveZeroes(int[] nums) {
        int pointer = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[pointer] = num;
                pointer++;
            }
        }
        for (int i = pointer; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
