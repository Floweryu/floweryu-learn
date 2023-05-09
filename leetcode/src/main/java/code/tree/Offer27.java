package code.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 27. 二叉树的镜像
 * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/description/
 * @author: zhangjunfeng
 * @createTime: 2023/05/09
 */
public class Offer27 {
    /**
     * 深度优先搜索：背着就行
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;

        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

    /**
     * 广度优先搜索：可以理解
     */
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            // 这里交换完node后，node的子树都被交换位置了
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }
}
