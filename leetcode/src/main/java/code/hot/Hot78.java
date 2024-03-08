package code.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * @author Floweryu
 * @date 2024/3/7 16:14:07
 */
public class Hot78 {
    private List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(int[] nums, int index, List<Integer> arr) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(arr));
            return;
        }
        arr.add(nums[index]);
        dfs(nums, index + 1, arr);
        arr.remove(arr.size() - 1);
        dfs(nums, index + 1, arr);
    }

    public static void main(String[] args) {
        Hot78 hot78 = new Hot78();
        hot78.subsets(new int[]{1, 2, 3});
    }
}
