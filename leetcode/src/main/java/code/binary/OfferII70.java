package code.binary;

/**
 * 剑指 Offer II 070. 排序数组中只出现一次的数字
 * https://leetcode.cn/problems/skFtm2/
 * 根据题目条件：有序数组、每个元素出现两次、有一个元素出现一次，可以推测：
 * 1. 单个元素所在位置x的左右都有偶数个元素，数组长度是奇数。
 * 2. 对x位置左侧元素y，如果nums[y]=nums[y+1]，则y一定是偶数；对于x位置右侧元素z，如果nums[z]=nums[z+1]，则z一定是奇数
 * 针对第二点，可以二分查找使用以下策略：
 * - 如果mid是奇数：则比较nums[mid]和nums[mid-1]，相等就说明单元素在mid右侧，因为mid左侧满足两两相等。不相等则说明单元素在mid左侧，因为有一个元素的存在打破了两两相等
 * - 如果mid是偶数：则比较nums[mid]和nums[mid+1]，相等就说明单元素在mid右侧，因为mid左侧满足两两相等。不相等则说明单元素在mid左侧，因为有一个元素的存在打破了两两相等
 * mid+1和mid-1可以使用mid^1(异或)来表示，这样就不用区分mid的奇偶性
 * 因为如果mid是奇数, 奇数和1异或会把末尾1抵消，就达到mid-1的效果
 * 如果mid是偶数，偶数和1异或会使末尾变成1，达到mid+1的效果
 * @author Floweryu
 * @date 2023/5/23 20:35
 */
public class OfferII70 {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                if (nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[right];
    }

    public int singleNonDuplicat2e(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[right];
    }

}
