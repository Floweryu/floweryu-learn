package code.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LCR 083. 全排列
 * https://leetcode.cn/problems/VvJkup/?envType=study-plan-v2&envId=coding-interviews-special
 * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
 * @author: zhangjunfeng
 * @createTime: 2023/08/10
 */
public class Offer083II {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            ans.add(num);
        }
        dfs(0, nums.length, ans);
        return res;
    }

    private void dfs(int start, int n, List<Integer> ans) {
        if (start == n) {
            res.add(new ArrayList<>(ans));
            return;
        }
        for (int i = start; i < n; i++) {
            Collections.swap(ans, start, i);
            dfs(start + 1, n, ans);
            Collections.swap(ans, start, i);
        }
    }

    public static void main(String[] args) {
        Offer083II offer083II = new Offer083II();
        List<List<Integer>> permute = offer083II.permute(new int[]{1, 2, 3});
    }
}
