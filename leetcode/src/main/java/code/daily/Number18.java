package code.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Floweryu
 * @date 2023/7/15 10:53
 */
public class Number18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            // 如果nums重复则跳过本次
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // i后面的三个数相加大于target直接跳出整体循环，说明后面没有符合条件的，因为一开始已经排过序了
            if ((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 数组后三个数和i相加还小于target，跳过本次，因为nums[i]太小了
            if ((long)nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                if ((long)nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }

                if ((long)nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < target) {
                    continue;
                }

                int left = j + 1, right = len - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ans.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        // 去重
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 上面循环出来left+1也是重复的，所以要去掉
                        left++;
                        // 去重
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        // 上面循环出来right-1也是重复的，所以要去掉
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Number18 number18 = new Number18();
        number18.fourSum(new int[]{0,0,0,1000000000,1000000000,1000000000,1000000000}, 1000000000);
    }
}
