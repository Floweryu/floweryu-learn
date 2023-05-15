package code.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 046. 二叉树的右侧视图
 * https://leetcode.cn/problems/WNC0Lk/
 * 层序遍历，遇到队列最后一个添加到结果集中
 * @author: zhangjunfeng
 * @createTime: 2023/05/12
 */
public class Offer46II {


    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 如果是队列最后一个则添加到结果集中
                if (i == size - 1) {
                    res.add(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return res;
    }
}
