package code.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/05
 */
public class Offer34 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target, new ArrayList<>());
        return res;
    }

    public void dfs(TreeNode root, int target, List<Integer> arr) {
        if (root == null) {
            return;
        }
        arr.add(root.val);
        dfs(root.left, target - root.val, arr);
        dfs(root.right, target - root.val, arr);

        if (target == root.val && root.left == null && root.right == null) {
            // 这里不能直接加入arr, 而是要复制一份
            // 因为直接加入是引用, arr后面还会变
            res.add(new ArrayList<>(arr));
            // 这里也不能return, 因为该节点不能影响其他节点, 还需要将它移除
        }
        // 如果当前叶子节点不满足target, 需要把他移除, 该节点不影响其他节点
        arr.remove(arr.size() - 1);
    }
}
