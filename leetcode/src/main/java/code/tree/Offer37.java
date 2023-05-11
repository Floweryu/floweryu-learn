package code.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 37. 序列化二叉树
 * https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/description/
 * @author: zhangjunfeng
 * @createTime: 2023/05/11
 */
public class Offer37 {

    // Encodes a tree to a single string. 递归将数节点转为string
    public String serialize(TreeNode root) {
        // 遇到空节点存为"null"
        if (root == null) {
            return "null,";
        }
        String res = root.val + ",";
        // 递归前序遍历
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 先将节点解析出来存到队列中
        String[] split = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for (String str : split) {
            queue.offer(str);
        }
        return deserialize(queue);
    }

    public TreeNode deserialize(Queue<String> queue) {
        String val = queue.poll();
        // "null"节点对应数的空节点，所以返回null
        if ("null".equals(val)) {
            return null;
        }
        // 先序构造一颗树
        TreeNode treeNode = new TreeNode(Integer.parseInt(val));
        treeNode.left = deserialize(queue);
        treeNode.right = deserialize(queue);
        return treeNode;
    }
}
