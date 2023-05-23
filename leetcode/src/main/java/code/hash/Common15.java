package code.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * https://leetcode.cn/problems/3sum/
 * @author Floweryu
 * @date 2023/5/23 22:24
 */
public class Common15 {
    public List<List<Integer>> threeSum(int[] nums) {
        // 先从小到大排序，为了把O(N^3)优化为O(N^2)
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            // 防止出现相邻两个元素一样被重复计算的情况，比如: -4 -1 -1 0 1 2，不加if[-1, 0, 1]会被计算两次
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1;
                int right = len - 1;
                while (left < right) {
                    // 找到一个结果
                    if (nums[i] + nums[left] + nums[right] == 0) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[left]);
                        tmp.add(nums[right]);
                        res.add(tmp);
                        // 将该重复的left去掉，也是为了防止重复结果
                        int curLeft = nums[left];
                        while (left < right && nums[left] == curLeft) {
                            left++;
                        }
                    } else if (nums[i] + nums[left] + nums[right] > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return res;
    }
}
