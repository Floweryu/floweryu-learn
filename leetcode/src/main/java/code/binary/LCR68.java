package code.binary;

/**
 * LCR 068. 搜索插入位置
 * https://leetcode.cn/problems/N6YdxV/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @date: 2023/12/08
 */
public class LCR68 {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
