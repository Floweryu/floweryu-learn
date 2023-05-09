package code.tree;

/**
 * 98. 验证二叉搜索树
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * @author: zhangjunfeng
 * @createTime: 2023/05/09
 */
public class Common98 {

    // 定义一个最小值
    long tmp = Long.MIN_VALUE;
    // 默认是二叉搜索树
    boolean res = true;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        isValidBST(root.left);
        // 根据二叉搜索树中序遍历是个有序数组的性质，可以在遍历过程中比较是否有序来判断
        if (tmp >= root.val) {
            res = false;
        }
        // 将当前值保留与下一次比较
        tmp = root.val;
        isValidBST(root.right);
        return res;
    }
}
