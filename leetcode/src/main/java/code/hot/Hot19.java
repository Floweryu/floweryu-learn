package code.hot;

import code.common.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * @author Floweryu
 * @date 2024/3/1 14:24:01
 */
public class Hot19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode header = new ListNode(-1);
        header.next = head;
        ListNode slow = header, fast = header;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return header.next;
    }
}