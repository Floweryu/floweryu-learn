package code.binary;

/**
 * @author zhangjunfeng
 * @date 2023/3/20 12:54
 */
public class Search162 {
    public int findPeakElement(int[] nums) {
        int left = 0;
        // 这里必须使用-1, 因为只有一个元素时, left等于right
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
