package code.common;

/**
 * 2. 两数相加
 * https://leetcode.cn/problems/add-two-numbers/description
 */
public class Common2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int ans = 0;
        ListNode header = new ListNode(-1);
        ListNode cur = header;
        // 巧妙的处理
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + ans;
            // 余数
            int rest = sum % 10;
            // 商
            ans = sum / 10;

            cur.next = new ListNode(rest);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (ans > 0) {
            cur.next = new ListNode(ans);
        }
        return header.next;
    }
}
