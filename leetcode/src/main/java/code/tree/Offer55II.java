package code.tree;

/**
 * 剑指 Offer 55 - II. 平衡二叉树
 * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/description/
 * @author: zhangjunfeng
 * @createTime: 2023/05/10
 */
public class Offer55II {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        // 如果当前节点左右子树深度相差小于等于1，则说明是平衡二叉树
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1) {
            // 接着递归遍历子节点, 并且左右子节点都要满足条件
            return isBalanced(root.left) && isBalanced(root.right);
        }
        return false;
    }

    /**
     * 获取左右子树中最大深度
     * 跳出递归条件: root=null, 深度返回0
     */
    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 获取当前节点左右子树深度最大值
        return Math.max(getHeight(root.left) + 1, getHeight(root.right) + 1);
    }
}
