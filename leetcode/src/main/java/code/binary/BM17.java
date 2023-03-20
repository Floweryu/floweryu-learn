package code.binary;

/**
 * @author zhangjunfeng
 * @date 2023/3/20 12:43
 */
public class BM17 {
    public int search (int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 这里不用 -1，因为右边界本来就没有值
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
