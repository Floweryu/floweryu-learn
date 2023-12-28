package code.common;

/**
 * 80. 删除有序数组中的重复项 II
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * @author: zhangjunfeng
 * @date: 2023/12/28
 */
public class Common80 {
    public int removeDuplicates(int[] nums) {
        // 记录真实数组位置
        int count = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[count - 2]) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }
}
