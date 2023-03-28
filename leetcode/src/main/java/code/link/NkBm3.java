package code.link;

/**
 * @author zhangjunfeng
 * @date 2023/3/28 12:57
 */
public class NkBm3 {
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) return head;
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        
        // 先找到头结点
        ListNode cur = preHead.next;
        // 保存下一个节点
        ListNode tmp;
        
        ListNode pre = preHead;
        // 一共需要反转len/k次
        for (int i = 0; i < len / k; i++) {
            // 这里因为cur = head所以从1算
            for (int j = 1; j < k; j++) {
                // 先保存下一个节点
                tmp = cur.next;
                // 当前节点和下下一个节点相连
                cur.next = tmp.next;
                // tmp指向第一个节点
                tmp.next = pre.next;
                // 此时tmp移到前面, 完成一次反转
                pre.next = tmp;
            }
            // 区间反转后, cur此时指向区间反转后最后一个节点, 所以pre指向cur作为下一个区间的前一个节点
            pre = cur;
            // 当前节点cur从新区间第一个节点开始
            cur = pre.next;
        }
        return preHead.next;
    }
    
    
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 构建一个假节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        // 指向每次要反转链表的头结点的上一个节点
        ListNode pre = dummy;
        // 指向每次要反转链表的尾结点
        ListNode end = dummy;
        while (end.next != null) {
            // 循环k次, 找到需要反转的链表的结尾, 需要判断end是否为空, 不然end.next会报错
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // end为null则说明反转的链表的节点数小于k, 不执行反转
            if (end == null) {
                break;
            }
            
            // 记录需要反转的链表的下一个节点
            ListNode next = end.next;
            // 断开要反转的链表
            end.next = null;
            // 记录要反转的链表的头结点
            ListNode start = pre.next;
            // 反转链表, pre.next指向反转后的链表
            pre.next = reverse(start);
            // 反转后, 原来的链表头结点变成了尾结点, 需要链接到之前的next
            start.next = next;
            // pre和end指向下一次要反转链表的前一个节点
            pre = start;
            end = start;
        }
        return dummy.next;
    }
    
    // 反转链表代码
    public ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
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
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
