package code.link;

/**
 * @author zhangjunfeng
 * @date 2023/3/23 12:54
 */
public class Common11 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode res = new ListNode();
        ListNode header = new ListNode(-1);
        header.next = head;
        ListNode cur = head;
        for (int i = 1; i < left; i++) {
            cur = cur.next;
        }
        ListNode subHead = cur;
        for (int i = left + 1; i <= right; i++) {
            cur = cur.next;
        }
        cur.next = null;
        
        return res;
    }
    
    public ListNode reverseLink(ListNode head) {
        return new ListNode();
    }
    
     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
