package code.link;

/**
 * 剑指 Offer II 022. 链表中环的入口节点
 * https://leetcode.cn/problems/c32eOV/description/
 * 快慢指针找到相遇位置并且同时能够判断是否有环
 * @author: zhangjunfeng
 * @createTime: 2023/04/06
 */
public class Offer022 {
    public ListNode detectCycle(ListNode head) {
        // 定义一个快慢指针
        ListNode fast = head, slow = head;
        // 遇到节点为空说明没有环
        while (fast != null && fast.next != null) {
            // 快指针每次走两步
            fast = fast.next.next;
            // 慢指针每次走一步
            slow = slow.next;
            // 如果两个指针相等，说明有环
            if (fast == slow) {
                // 在相等节点处, 相遇指针继续向下走的距离就等于起始指针走到环入口的距离
                ListNode res = head;
                while (res != slow) {
                    res = res.next;
                    slow = slow.next;
                }
                return res;
            }
        }
        return null;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
