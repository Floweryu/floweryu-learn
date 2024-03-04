package code.hot;

import code.common.ListNode;

import java.util.*;

/**
 * 138. 随机链表的复制
 * @author Floweryu
 * @date 2024/3/4 11:16:38
 */
public class Hot138 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node ans = new Node(-1);
        Node pointer = ans, cur = head;
        while (cur != null) {
            // 先构造每个节点的映射：
            // node->node
            // random->random
            Node node = map.getOrDefault(cur, new Node(cur.val));
            map.put(cur, node);
            if (cur.random != null) {
                node.random = map.getOrDefault(cur.random, new Node(cur.random.val));
                map.put(cur.random, node.random);
            }
            pointer.next = node;
            cur = cur.next;
            pointer = pointer.next;
        }
        return ans.next;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
