package code.common;

import java.util.HashMap;

/**
 * 169. 多数元素
 * https://leetcode.cn/problems/majority-element/description/?envType=study-plan-v2&envId=top-interview-150
 * @author: zhangjunfeng
 * @date: 2023/12/28
 */
public class Common169 {
    public int majorityElement(int[] nums) {
        int target = nums.length / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > target) {
                return num;
            }
        }
        return -1;
    }
}
