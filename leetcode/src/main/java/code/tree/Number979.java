package code.tree;

/**
 * 979. 在二叉树中分配硬币
 * https://leetcode.cn/problems/distribute-coins-in-binary-tree/
 * @author: zhangjunfeng
 * @createTime: 2023/07/14
 */
public class Number979 {
    int move = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return move;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = 0, right = 0;
        if (root.left != null) {
            left = dfs(root.left);
        }
        if (root.right != null) {
            right = dfs(root.right);
        }

        move += Math.abs(left) + Math.abs(right);

        return left + right + root.val - 1;
    }
}