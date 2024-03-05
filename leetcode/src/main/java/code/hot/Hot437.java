package code.hot;

import code.tree.TreeNode;

/**
 * 437. 路径总和 III
 * @author Floweryu
 * @date 2024/3/5 17:43:55
 */
public class Hot437 {
    int ans = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        dpTree(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return ans;
    }

    private void dpTree(TreeNode root, long targetSum){
        if (root == null) {
            return;
        }
        if (targetSum == root.val) {
            ans++;
        }
        dpTree(root.left, targetSum - root.val);
        dpTree(root.right, targetSum - root.val);
    }
}
