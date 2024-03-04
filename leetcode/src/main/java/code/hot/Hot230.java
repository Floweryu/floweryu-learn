package code.hot;

import code.tree.TreeNode;

/**
 * @author Floweryu
 * @date 2024/3/4 21:53
 */
public class Hot230 {
    int ans, count;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        midOrder(root);
        return ans;
    }

    private void midOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
        if (count == 1) {
            ans = root.val;
        }
        count--;
        midOrder(root.right);
    }
}
