package code.tree;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/description/
 * 算法流程：
 * 1. 中序遍历：二叉搜索树中序遍历结果是一个有序集
 * 2. 递归左子树，即midDfs(root.left)
 * 3. 构造链表：
 *      a. 当pre不为空时，修改双向节点引用，即：pre.right = node, node.left = pre
 *      b. 当pre为空时，表示正在访问头结点，即为head
 *      c. 保存node节点，作为下一个pre节点
 * 4. 递归右子树，即midDfs(root.right)
 * 5. 递归完成后，需要将头尾节点相连，此时pre就是尾结点。即：pre.right = head, head.left = pre
 * @author: zhangjunfeng
 * @createTime: 2023/05/08
 */
public class Offer36 {
    Node head, pre;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        midDfs(root);
        pre.right = head;
        head.left = pre;
        return head;
    }

    public void midDfs(Node node) {
        if (node == null) {
            return;
        }
        midDfs(node.left);
        if (pre != null) {
            pre.right = node;
        } else {
            // 说明是第一个节点
            head = node;
        }
        node.left = pre;
        pre = node;
        midDfs(node.right);
    }
}
