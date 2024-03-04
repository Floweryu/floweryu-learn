package code.hot;

import code.tree.TreeNode;

/**
 * 543. 二叉树的直径
 * @author Floweryu
 * @date 2024/3/4 19:09:34
 */
public class Hot543 {
    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depthTree(root);
        return ans;
    }

    private int depthTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树深度
        int l = depthTree(root.left);
        // 右子树深度
        int r = depthTree(root.right);
        // 更新ans值，最大直径有可能在此递归过程中
        ans = Math.max(ans, l + r);
        return Math.max(l, r) + 1;
    }
}
