package code.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 081. 允许重复选择元素的组合
 * https://leetcode.cn/problems/Ygoe9J/description/?envType=study-plan-v2&envId=coding-interviews-special
 * <p>
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * <p>
 * 思路：定义递归函数dfs(int[] candidates, List<Integer> tmp, int index, int target)
 * 递归跳出条件：
 * 1. 数组元素遍历完，即index==candidates.length
 * 2. target刚好等于0，说明找到一个结果，即target==0
 * 递归逻辑：
 * 1. 选择当前值，则index不变，target-当前值，继续递归。
 * 2. 不选择当前值，index+1，target不变，继续递归。
 * <p>
 * 总结: 可以参考求子集合dfs模板
 * @author: zhangjunfeng
 * @createTime: 2023/07/19
 */
public class OfferII81 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, new ArrayList<>(), 0, target);
        return res;
    }

    public void dfs(int[] candidates, List<Integer> tmp, int index, int target) {
        // 退出递归条件
        if (index == candidates.length) {
            return;
        }

        // 得到结果并退出递归的条件
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        // 选当前值
        if (target - candidates[index] >= 0) {
            tmp.add(candidates[index]);
            // index值不变, 因为每个元素可以重复获取；由于选择了当前值，所以target需要减去当前值
            dfs(candidates, tmp, index, target - candidates[index]);
            // 进行回溯
            tmp.remove(tmp.size() - 1);
        }

        // 不选当前值, index+1，target值不变
        dfs(candidates, tmp, index + 1, target);
    }
}
