package code.link;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * @author Floweryu
 * @date 2023/4/6 22:51
 */
public class Offer22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode cur = head;
        // 先求链表长度, 这里是0，因为结束条件是cur!=null, 会多遍历一次
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        cur = head;
        // 计算从头到k需要走几步: len - k
        for (int i = 1; i <= len - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
