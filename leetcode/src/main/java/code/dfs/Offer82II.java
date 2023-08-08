package code.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/4sjJUc/?envType=study-plan-v2&envId=coding-interviews-special
 * 剑指 Offer II 082. 含有重复元素集合的组合
 * @author: zhangjunfeng
 * @createTime: 2023/07/27
 */
public class Offer82II {

    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] vis;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序是为了后面驱虫
        Arrays.sort(candidates);
        // 初始化访问数组
        vis = new boolean[candidates.length];
        dfs(candidates, new ArrayList<>(), target, 0);
        return res;
    }

    public void dfs(int[] nums, List<Integer> ans, int target, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int i = index; i < nums.length && nums[i] <= target; i++) {
            // 每次在向下递归时，跳过相同数字
            // 例如：1   1   2   5   6   7   10
            // 第一次： 从1开始递归
            // 第二次： 由于数组中还是1，但上一次1已经访问过了，将跳过，从2开始向下递归
            if (!vis[i] && (i == 0 || nums[i] != nums[i - 1] || vis[i - 1])) {
                vis[i] = true;
                ans.add(nums[i]);
                dfs(nums, ans, target - nums[i], i + 1);
                ans.remove(ans.size() - 1);
                vis[i] = false;
            }
        }
    }
}
