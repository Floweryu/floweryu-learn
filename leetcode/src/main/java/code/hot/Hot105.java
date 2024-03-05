package code.hot;

import code.tree.TreeNode;

import java.util.HashMap;

/**
 * 先序遍历：根->左->右
 * 中序遍历：左->根->右
 * @author Floweryu
 * @date 2024/3/5 10:22:15
 */
public class Hot105 {
    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int rootIndex = map.get(preorder[preLeft]);
        if (preLeft != preRight) {
            root.left = buildTree(preorder, preLeft + 1, preLeft + rootIndex - inLeft, inorder, inLeft, rootIndex - 1);
            root.right = buildTree(preorder, preLeft + rootIndex - inLeft + 1, preRight, inorder, rootIndex + 1, inRight);
        }
        return root;
    }
}
