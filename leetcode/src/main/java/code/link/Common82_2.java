package code.link;

/**
 * 82. 删除排序链表中的重复元素 II
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/
 * @author: zhangjunfeng
 * @createTime: 2023/04/28
 */
public class Common82_2 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        /**
         * 关键点: cur指针始终指向前置元素
         * 由于是升序链表, 设当前节点是cur, 直接比较cur.next和cur.next.next值是否相等即可确定是否需要删除
         *      -1 -> 1 -> 1 -> 1 -> 2 -> 3
         *      cur  ptr ...........ptr
         */
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                // 遍历指针, 跳出循环指向下一个不重复元素
                ListNode ptr = cur.next;
                while (ptr != null && ptr.val == cur.next.val) {
                    ptr = ptr.next;
                }
                /**
                 * 考虑到下面这种情况, 下一次循环仍需要丢弃2元素
                 *      -1 -> 1 -> 1 -> 1 -> 2 -> 2
                 *      cur  ptr ...........ptr
                 */
                cur.next = ptr;
            } else {
                // 不是重复元素, cur后移
                cur = cur.next;
            }
        }
        return dummy.next;
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
