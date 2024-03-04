package code.hot;

import code.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 114. 二叉树展开为链表
 * @author Floweryu
 * @date 2024/3/4 22:23
 */
public class Hot114 {
    private List<TreeNode> list = new ArrayList<>();
    public void flatten(TreeNode root) {
        preOrder(root);
        for (int i = 0; i < list.size() - 1; i++) {
            // 这里第一个node和root指向相同的地址
            TreeNode node = list.get(i);
            node.left = null;
            node.right = list.get(i + 1);
        }
    }

    private void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root);
        preOrder(root.left);
        preOrder(root.right);
    }
}
