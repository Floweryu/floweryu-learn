package code.tree;

/**
 * 617. 合并二叉树
 * https://leetcode.cn/problems/merge-two-binary-trees/description/
 * @author: zhangjunfeng
 * @createTime: 2023/05/09
 */
public class Common617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode merge = new TreeNode(root1.val + root2.val);
        merge.left = mergeTrees(root1.left, root2.left);
        merge.right = mergeTrees(root1.right, root2.right);
        return merge;
    }
}
