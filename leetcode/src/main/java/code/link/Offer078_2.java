package code.link;

/**
 * 剑指 Offer II 078. 合并排序链表：https://leetcode.cn/problems/vvXgSW/description/
 * 归并
 * @author Floweryu
 * @date 2023/4/5 22:48
 */
public class Offer078_2 {

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeSort(lists, 0, lists.length - 1);
    }

    public ListNode mergeSort(ListNode[] list, int i, int j) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return list[i];
        }
        int mid = (i + j) / 2;
        ListNode l1 = mergeSort(list, i, mid);
        ListNode l2 = mergeSort(list, mid + 1, j);
        return mergeTwoList(l1, l2);
    }

    public ListNode mergeTwoList(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }

        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                head.next = a;
                a = a.next;
            } else {
                head.next = b;
                b = b.next;
            }
            head = head.next;
        }
        head.next = a == null ? b : a;
        return dummy.next;
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
