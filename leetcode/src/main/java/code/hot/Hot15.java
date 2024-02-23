package code.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * [-4, -1, -1, 0, 1, 2]
 * @author Floweryu
 * @date 2024/2/21 20:08:46
 */
public class Hot15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            // 去掉重复的
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[left]);
                    tmp.add(nums[right]);
                    ans.add(tmp);
                    // 去掉重复的
                    int val = nums[left];
                    while (left < right && nums[left] == val) {
                        left++;
                    }
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }
}
