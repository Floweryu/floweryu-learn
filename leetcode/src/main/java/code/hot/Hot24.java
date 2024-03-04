package code.hot;

import code.common.ListNode;

/**
 * 24. 两两交换链表中的节点
 * @author Floweryu
 * @date 2024/3/1 15:45:37
 */
public class Hot24 {
    public ListNode swapPairs(ListNode head) {
        ListNode preHead = new ListNode(-1);
        preHead.next = head;

        ListNode pre = preHead;

        while (pre.next != null && pre.next.next != null) {

            ListNode first = pre.next;
            ListNode second = first.next;
            // 交换相邻节点位置
            pre.next = second;
            first.next = second.next;
            second.next = first;

            pre = first;
        }
        return preHead.next;
    }
}
