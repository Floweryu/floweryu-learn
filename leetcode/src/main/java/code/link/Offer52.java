package code.link;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/description/
 * @author: zhangjunfeng
 * @createTime: 2023/04/18
 */
public class Offer52 {
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            // 这里如果对pA.next进行判空，就会进入无限循环出不来
            // 假设链表如下：
            // 1 -> 3 -> 5 -> null
            //      2 -> 4 -> null
            // 如果对pA.next判空，则每次指针到5或4都会跳转到2或1,
            // 但如果对pA本身判空，则总会存在一个null相等跳出循环
            // pA = pA.next == null ? headB : pA.next;
            // pB = pB.next == null ? headA : pB.next;
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
