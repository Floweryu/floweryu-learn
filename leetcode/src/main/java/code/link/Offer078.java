package code.link;

/**
 * @author Floweryu
 * @date 2023/4/5 21:58
 */
public class Offer078 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists) {
            ans = mergTwoList(ans, list);
        }
        return ans;
    }

    /**
     * 合并两个有序链表算法
     */
    private ListNode mergTwoList(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                head.next = a;
                a = a.next;
            } else {
                head.next = b;
                b = b.next;
            }
            head = head.next;
        }
        // 这里直接将剩余链表接上就行
        head.next = a == null ? b : a;
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
