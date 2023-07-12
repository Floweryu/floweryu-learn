package code.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 080. 含有 k 个元素的组合
 * https://leetcode.cn/problems/uUsW3B/description/?envType=study-plan-v2&envId=coding-interviews-special
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * @author: zhangjunfeng
 * @createTime: 2023/07/12
 */
public class Offer80II {

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> tmp = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, k, n);
        return res;
    }

    public void dfs(int i, int k, int n) {
        // 递归结束条件：当前tmp元素个数=k
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        // 什么情况下递归？当i<=n时
        if (i <= n) {
            // 选择i
            tmp.add(i);
            // 继续递归
            dfs(i + 1, k, n);
            // 为什么要回溯？当选了[1,2]时，还有[1,3]等需要选，所以需要把3回退
            tmp.remove(tmp.size() - 1);
            dfs(i + 1, k, n);
        }
    }

    public static void main(String[] args) {
        Offer80II offer80II = new Offer80II();
        offer80II.combine(4, 2);
    }
}
