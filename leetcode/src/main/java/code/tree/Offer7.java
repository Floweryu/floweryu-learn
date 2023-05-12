package code.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 07. 重建二叉树
 * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
 * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 1. 先从前序遍历获取树的根节点，在中序遍历中根据根节点可以判断该根节点的左右子树节点以及个数
 * 2. 根据上一步获得的左右子树个数，可以获取前序遍历中该根节点的左右子树个数
 * @author: zhangjunfeng
 * @createTime: 2023/05/11
 */
public class Offer7 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, length - 1, inorder, 0, length - 1, map);
    }

    /**
     *
     * @param preorder 前序遍历数组
     * @param startPreOrder 当前根节点对应的子树节点在前序数组中的起始下标
     * @param endPreOrder 当前根节点对应的子树节点在前序数组中的终止下标
     * @param inorder 中序遍历数组
     * @param startInOrder 当前根节点对应的子树节点在中序数组中的起始下标
     * @param endInOrder 当前根节点对应的子树节点在中序数组中的终止下标
     * @param map 中序数组节点和下标映射关系，方便获取节点在中序中的位置
     * @return
     */
    public TreeNode buildTree(int[] preorder, int startPreOrder, int endPreOrder, int[] inorder, int startInOrder, int endInOrder, Map<Integer, Integer> map) {
        // 判断边界
        if (startPreOrder > endPreOrder) {
            return null;
        }
        // 从先序遍历中获取根节点
        int rootVal = preorder[startPreOrder];
        TreeNode root = new TreeNode(rootVal);
        // 如果先序遍历节点是最后一个
        if (startPreOrder != endPreOrder) {
            // 获取根节点在中序遍历中的下标index
            // [startInOrder, index-1]是当前root的左子树
            // [index+1, endInOrder]是当前root的右子树
            Integer index = map.get(rootVal);
            // 计算前序遍历中左右子树分界线
            // 根据节点在中序遍历中的位置可以确定该节点左右子树的节点个数
            // 然后在前序遍历中根据左右子树个数把前序遍历分成左右子树
            int leftNodes = index - startInOrder;
            int rightNodes = endInOrder - index - 1;
            root.left = buildTree(preorder, startPreOrder + 1, startPreOrder + leftNodes, inorder, startInOrder, index - 1, map);
            root.right = buildTree(preorder, endPreOrder - rightNodes, endPreOrder, inorder, index + 1, endInOrder, map);
        }
        return root;
    }
}
