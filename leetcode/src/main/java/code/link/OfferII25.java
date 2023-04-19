package code.link;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/04/18
 */
public class OfferII25 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 先将链表反转
        ListNode r1 = reverseListNode(l1);
        ListNode r2 = reverseListNode(l2);
        // 创建一个空的尾结点，结果从后面插入节点
        ListNode tail = null;
        ListNode cur1 = r1, cur2 = r2;
        // 设置进位标志
        int add = 0;
        // 同时遍历两个链表公共部分
        while (cur1 != null || cur2 != null || add != 0) {
            int val1 = cur1 == null ? 0 : cur1.val;
            int val2 = cur2 == null ? 0 : cur2.val;
            // 相加并带上进位，然后取余
            int rest = (val1 + val2 + add) % 10;
            // 计算进位
            add = (val1 + val2 + add) / 10;
            // 构造一个节点
            ListNode c = new ListNode(rest);
            // 从尾部插入链表
            c.next = tail;
            tail = c;

            // 指针不为空则下移
            if (cur1 != null) {
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                cur2 = cur2.next;
            }
        }
        return tail;
    }
    
    public ListNode reverseListNode(ListNode head) {
        ListNode pre = null, cur = head;
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
        ListNode(int val) { this.val = val; }
    }
}
