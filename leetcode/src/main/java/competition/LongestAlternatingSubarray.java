package competition;

/**
 * @author Floweryu
 * @date 2023/7/2 11:11
 */
public class LongestAlternatingSubarray {
    int res = 0, index = -1;
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        dfs(nums, 0, nums.length - 1, threshold);
        return res;
    }

    private void dfs(int[] nums, int i, int j, int threshold) {
        if (nums[i] % 2 != 0) {
            dfs(nums, i + 1, j, threshold);
        } else {
            if (check(nums, i, j, threshold, index)) {
                System.out.println(i + "--------" + j);
                res = Math.max(res, j - i + 1);
            } else {
                dfs(nums, i, index - 1, threshold);
                dfs(nums, index + 1, j, threshold);
            }
        }

    }

    private boolean check(int[] arr, int i, int j, int threshold, int index) {
        for (int x = i; x <= j; x++) {
            if (arr[x] > threshold) {
                index = x;
                return false;
            }
            if (x < j && arr[i] % 2 == arr[i + 1] % 2) {
                index = x;
                return false;
            }
        }
        return true;
    }
}
