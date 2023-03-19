package code.binary;

/**
 * 35. 搜索插入位置
 * https://leetcode.cn/problems/search-insert-position/description/
 * 思路：
 * 返回将要被插入的位置，其实就是寻找到target的最左边界, 找到最接近小于target的值的位置
 * @author zhangjunfeng
 * @date 2023/3/16 12:48
 */
public class Search35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
