package code.hot;

import code.common.ListNode;

/**
 * 148. 排序链表
 * @author Floweryu
 * @date 2024/2/21 20:34:38
 */
public class Hot148 {

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {

        if (head == null) {
            return null;
        }

        if (head.next == tail) {
            head.next = null;
            return head;
        }

        ListNode fast = head, slow = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode l1 = sortList(head, mid);
        ListNode l2 = sortList(mid, tail);
        ListNode merged = merge(l1, l2);
        return merged;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode preHeader = new ListNode(-1);
        ListNode pointer = preHeader;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pointer.next = l1;
                l1 = l1.next;
            } else {
                pointer.next = l2;
                l2 = l2.next;
            }
            pointer = pointer.next;
        }
        pointer.next = l1 == null ? l2 : l1;
        return preHeader.next;
    }
}
