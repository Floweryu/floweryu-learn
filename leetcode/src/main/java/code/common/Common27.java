package code.common;

/**
 * @author: zhangjunfeng
 * @date: 2023/12/27
 */
public class Common27 {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}
