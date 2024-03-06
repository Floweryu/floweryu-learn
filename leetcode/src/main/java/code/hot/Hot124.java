package code.hot;

import code.tree.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * @author Floweryu
 * @date 2024/3/5 20:20:38
 */
public class Hot124 {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        deepTree(root);
        return ans;
    }

    private int deepTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 递归求左右节点最大值，当值大于0时才取
        int leftVal = Math.max(deepTree(root.left), 0);
        int rightVal = Math.max(deepTree(root.right), 0);

        // 节点最大值路径取决于：该节点值 与 该节点左右子节点的最大贡献值
        int sum = leftVal + rightVal + root.val;
        // 更新答案
        ans = Math.max(ans, sum);

        // 递归返回当前节点的最大贡献值
        return root.val + Math.max(leftVal, rightVal);
    }
}
