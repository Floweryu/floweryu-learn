package code.tree;

/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * 解题思路：
 * 二叉搜索树性质: 左子树都小于root, 右子树都大于root
 * 根据上面性质，如果要找最近公共祖先节点，则该节点一定大于左子树，小于右子树
 * - 如果两个节点都大于root节点，则说明公共祖先在右子树，递归右子树即可
 * - 如果两个节点都小于root节点，则说明公共祖先在左子树，递归左子树即可
 * @author: zhangjunfeng
 * @createTime: 2023/05/10
 */
public class Offer68i {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}
