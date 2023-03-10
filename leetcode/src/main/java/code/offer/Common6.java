package code.offer;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/?favorite=xb9nqhhg
 * @author zhangjunfeng
 * @date 2023/3/8 19:34
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Common6 {

    public int[] reversePrint(ListNode head) {
        List<Integer> res = new ArrayList<>();
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
        Collections.reverse(res);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
    
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
