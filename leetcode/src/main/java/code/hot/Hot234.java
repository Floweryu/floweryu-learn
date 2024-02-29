package code.hot;

import code.common.ListNode;

/**
 * 234. 回文链表
 * 先寻找中间节点，然后反转后面部分，最后和前半部分比较
 * 注意：不能直接反转整个链表，因为这样会改变原来链表结构
 * @author Floweryu
 * @date 2024/2/29 19:26:16
 */
public class Hot234 {
    public boolean isPalindrome(ListNode head) {

        ListNode fast = head, slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }

        ListNode cur = slow,  pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        ListNode tmpHead = head;
        while (pre != null && tmpHead != null) {
            if (pre.val != tmpHead.val) {
                return false;
            }
            pre = pre.next;
            tmpHead = tmpHead.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        Hot234 hot234 = new Hot234();
        boolean palindrome = hot234.isPalindrome(head);
    }
}
