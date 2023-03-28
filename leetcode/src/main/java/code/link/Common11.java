package code.link;

/**
 * @author zhangjunfeng
 * @date 2023/3/23 12:54
 */
public class Common11 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 新增一个虚拟节点, 链表问题这样处理方便
        ListNode header = new ListNode(-1);
        header.next = head;
        
        ListNode pre = header;
        // 为什么要寻找到left的前一个节点?
        // 因为后面区间链表反转后需要通过前一个节点接上
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        
        // 寻找区间右节点
        ListNode rightNode = pre;
        for (int i = left; i <= right; i++) {
            rightNode = rightNode.next;
        }
        
        // 记录区间左节点
        ListNode leftNode = pre.next;
        // 记录区间右节点的下一个节点
        // 为了区间链表反转后接上
        ListNode tailNode = rightNode.next;
        // 切断链表关系
        pre.next = null;
        rightNode.next = null;
        
        reverseLink(leftNode);
        
        // 经过反转后, 之前区间的rightNode变成了头结点
        pre.next = rightNode;
        // 区间的尾结点变成了头结点
        leftNode.next = tailNode;
        return header.next;
    }
    
    public void reverseLink(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            // 先将下一个节点保存
            ListNode next = cur.next;
            // 当前节点的next指向尾结点
            cur.next = pre;
            // pre指向当前节点, 作为尾结点
            pre = cur;
            // 当前指针后移一个节点
            cur = next;
        }
    }
    
     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
