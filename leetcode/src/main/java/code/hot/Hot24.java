package code.hot;

import code.common.ListNode;

/**
 * @author Floweryu
 * @date 2024/3/1 15:45:37
 */
public class Hot24 {
    public ListNode swapPairs(ListNode head) {
        ListNode preHead = new ListNode(-1);
        preHead.next = head;

        ListNode firstPre = preHead, second = firstPre.next.next;

        ListNode first = firstPre.next;
        firstPre.next = first.next;
        first.next = second.next;
        second.next = first;
        for (int i = 0; i < 2; i++) {
            firstPre = firstPre.next;
        }

    }
}
