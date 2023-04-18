package code.link;

/**
 * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
 * https://leetcode.cn/problems/SLwz0R/description/
 * @author Floweryu
 * @date 2023/4/6 23:03
 */
public class Offer21 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        ListNode dumy = new ListNode(-1);
        dumy.next = head;
        ListNode pre = dumy;
        // 寻找到要删除节点的前一个节点
        for (int i = 1; i <= len - n; i++) {
            pre = pre.next;
        }
        // 得到要删除的节点
        cur = pre.next;
        // 这里需要判空
        if (cur != null) {
            pre.next = cur.next;
        }
        return dumy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
