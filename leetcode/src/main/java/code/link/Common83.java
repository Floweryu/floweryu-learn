package code.link;

import java.util.HashSet;
import java.util.Set;

/**
 * 83. 删除排序链表中的重复元素
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/
 * @author: zhangjunfeng
 * @createTime: 2023/04/27
 */
public class Common83 {

    public ListNode deleteDuplicates(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // cur: 遍历指针; pre: 因为要删除元素，所以需要一个前置指针指向第一个不重复元素, 如: [1, 1, 1] pre指向第一个1
        ListNode cur = head, pre = cur;
        while (cur != null) {
            if (set.contains(cur.val)) {
                // 遇到重复元素, 直接删除
                pre.next = cur.next;
            } else {
                // 遇到不重复元素, 将pre指针指向当前
                set.add(cur.val);
                pre = cur;
            }
            // 遍历
            cur = cur.next;
        }
        return dummy.next;
    }

    public static class ListNode {
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
