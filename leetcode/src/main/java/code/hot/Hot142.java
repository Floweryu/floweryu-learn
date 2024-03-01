package code.hot;

import code.common.ListNode;

/**
 * 142. 环形链表 II
 * @author Floweryu
 * @date 2024/3/1 09:41:41
 */
public class Hot142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            if (fast == slow && fast != null) {
                ListNode cur = head;
                while (cur != slow) {
                    cur = cur.next;
                    slow = slow.next;
                }
                return cur;
            }
        }
        return null;
    }
}
