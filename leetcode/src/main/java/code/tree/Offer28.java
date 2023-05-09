package code.tree;

/**
 * 剑指 Offer 28. 对称的二叉树
 * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/08
 */
public class Offer28 {
    public boolean isSymmetric(TreeNode root) {
        // 根节点为null, 返回true
        if (root == null) return true;
        // 递归遍历左右子树
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode left, TreeNode right) {
        // 左节点和右节点都为空, 返回true
        if (left == null && right == null) return true;
        // 左节点和右节点有一个为空, 返回false
        if (left == null || right == null) return false;
        // 节点值不相等 返回false
        if (left.val != right.val) return false;
        // 判断 left节点的左节点和right的右节点 并且 left的右节点和right的左节点 是否对称
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
