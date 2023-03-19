package code.binary;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * @author zhangjunfeng
 * @date 2023/3/15 20:11
 */
public class Search34 {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        // 寻找最左边界
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] >= target) {
                // 这时不需要mid-1, 因为右区间本来就不存在数字
                right = mid;
            }
        }
        // 判断一下边界和是否等于target
        if (left < nums.length && nums[left] == target) res[0] = left;
    
        // 寻找最右边界
        left = 0;
        right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                // 因为是寻找右边界, 所以left相等也先舍弃
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 这时不需要mid-1, 因为右区间本来就不存在数字
                right = mid;
            }
        }
        // 判断一下边界和是否等于target
        if (right > 0 && nums[right - 1] == target) res[1] = right - 1;
        
        return res;
    }
    
    public int[] searchRangePro(int[] nums, int target) {
        // 寻找左边界
        int left = binarySearch(nums, target, true);
        // 寻找右边界，由于返回的是第一个>=target 但是索引+1的值, 所以要减1（参考left=mid+1后才返回，这时多加了个1）
        int right = binarySearch(nums, target, false) - 1;
        if (left < nums.length && right >= 0 && nums[left] == target && nums[right] == target) {
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }
    
    // flag=true表示寻找最左边界
    // 左右边界都可以返回left. 原因: 因为结束条件是left < right, 所以跳出循环时一定是left=right, 所以返回left和right都行
    public int binarySearch(int[] nums, int target, boolean flag) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (flag && nums[mid] >= target)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    public static void main(String[] args) {
        Search34 search34 = new Search34();
        int[] nums = new int[]{5,7,7,8,8,10};
        int[] ints = search34.searchRange(nums, 6);
    }
}
