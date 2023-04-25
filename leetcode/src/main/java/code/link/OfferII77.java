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
     * 1. 找到链表中点，以中点为分界。找链表中点方法：快慢指针，快指针每次移2步、慢指针每次移1步，当快指针到达链表末尾时，慢指针到达链表中点
     * 2. 对两个子链表进行排序
     * 3. 将两个排序后的子链表合并，得到排序后的链表
     * 时间复杂度：nlog(n)
     * 空间复杂度：log(n)
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
     * 2. 自底向上归并排序
     * 核心思想：每次将链表分成子链表进行两两合并排序，最初是1, 后面依次为[2, 4, 8, ...]
     */
    public ListNode sortList2(ListNode head) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        // 存储最终链表
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode curr = dummy.next, prev = dummy;
            while (curr != null) {
                // 寻找一个链表头结点head1
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                // 寻找另一个链表头结点
                ListNode head2 = curr.next;
                // 剪断第一个链表head1与原始链表关系
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                // 先记录第二个链表head2的下一个节点, 然后减掉第二个链表head2与原始链表关系
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                // 合并两个子链表
                ListNode merge = merge(head1, head2);
                // 将合并后的新链表接到结果链表上
                prev.next = merge;
                while (prev.next != null) {
                    prev = prev.next;
                }
                // 重新赋值curr，准备开始当前长度subLength的下一次链表排序合并
                curr = next;
            }
        }
        return dummy.next;
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
