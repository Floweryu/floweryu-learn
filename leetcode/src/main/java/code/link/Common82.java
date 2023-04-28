package code.link;

import java.util.HashSet;
import java.util.Set;

/**
 * 82. 删除排序链表中的重复元素 II
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
 * @author: zhangjunfeng
 * @createTime: 2023/04/27
 */
public class Common82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 先去一遍重: 例如原链表: [1, 1, 1, 2, 2, 3, 4, 5]
        // 第一遍去重后: [1, 2, 3, 4, 5], 同时将重复元素记录到tSet中
        ListNode cur = dummy.next, pre = cur;
        Set<Integer> set = new HashSet<>();
        Set<Integer> tSet = new HashSet<>();
        while (cur != null) {
            if (set.contains(cur.val)) {
                pre.next = cur.next;
                tSet.add(cur.val);
            } else {
                set.add(cur.val);
                pre = cur;
            }
            cur = cur.next;
        }
        // 第二遍去重: 遍历第一遍去重后链表判断元素是否在tSet中, 有则需要再去掉该元素
        // 由于链表第一个元素有可能需要去掉, 所以需要记录-1节点位置
        ListNode ptr = dummy, pro = ptr;
        while (ptr != null) {
            if (tSet.contains(ptr.val)) {
                pro.next = ptr.next;
            } else {
                pro = ptr;
            }
            ptr = ptr.next;
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
