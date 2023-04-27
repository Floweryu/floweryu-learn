package code.link;

/**
 * 328. 奇偶链表
 * https://leetcode.cn/problems/odd-even-linked-list/
 * @author: zhangjunfeng
 * @createTime: 2023/04/27
 */
public class Common328 {
    public ListNode oddEvenList(ListNode head) {
        // 存储奇数链表
        ListNode odd = new ListNode(-1);
        // 存储偶数链表
        ListNode even = new ListNode(-1);
        // 判断当前是奇数索引节点还是偶数索引节点
        int index = 0;
        // cur: 遍历链表; oddRes: 接收奇数节点; evenRes: 接收偶数节点
        ListNode cur = head, oddRes = odd, evenRes = even;
        while (cur != null) {
            index++;
            if (index % 2 != 0) {
                // 如果是奇数索引节点, 连接到奇数链表中
                oddRes.next = new ListNode(cur.val);
                oddRes = oddRes.next;
            } else {
                // 如果是偶数索引节点, 连接到偶数链表中
                evenRes.next = new ListNode(cur.val);
                evenRes = evenRes.next;
            }
            cur = cur.next;
        }
        // 最后将偶数结果链表接到奇数结果链表中返回
        oddRes.next = even.next;
        return odd.next;
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
