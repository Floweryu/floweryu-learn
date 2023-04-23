package code.link;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/04/20
 */
public class OfferII77 {

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }

        if (head.next == tail) {
            head.next = null;
            return head;
        }

        // 快慢指针寻找链表中间节点
        // fast速度是slow两倍, 所以当fast到tail节点时, slow在中间节点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            // 慢指针走一步
            slow = slow.next;
            // 快指针走两步
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode l1 = sortList(head, mid);
        ListNode l2 = sortList(mid, tail);
        ListNode res = merge(l1, l2);
        return res;
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            if (v1 > v2) {
                cur.next = l2;
            } else {
                cur.next = l1;
            }
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
