package code.link;

/**
 * 剑指 Offer II 077. 链表排序
 * https://leetcode.cn/problems/7WHec2/description/
 * @author: zhangjunfeng
 * @createTime: 2023/04/20
 */
public class OfferII77 {

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    /**
     * 方法一：自顶向下归并排序
     * @param head
     * @param tail
     * @return
     */
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

    /**
     * 归并排序，要求背诵
     */
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
