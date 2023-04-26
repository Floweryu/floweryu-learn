package code.link;

/**
 * 剑指 Offer II 027. 回文链表
 * https://leetcode.cn/problems/aMhZSa/
 * 注意：这题不能直接将整个链表反转然后比较，因为反转整个链表后head指针就变了（即原链表就变了）
 * 例如： 1->2->3->4， 反转后：1<-2<-3<-4，反转后head指向1，但是head.next为空了
 * @author: zhangjunfeng
 * @createTime: 2023/04/26
 */
public class OfferII27 {
    public boolean isPalindrome(ListNode head) {
        // 找中点
        ListNode slow = head, fast = head;
        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        // 反转中点以后的链表
        ListNode reverseLink = reverseLink(slow);
        ListNode cur = head, reverseCur = reverseLink;
        while (cur != null && reverseCur != null) {
            if (cur.val != reverseCur.val) {
                return false;
            }
            cur = cur.next;
            reverseCur = reverseCur.next;
        }
        return true;
    }

    public ListNode reverseLink(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
