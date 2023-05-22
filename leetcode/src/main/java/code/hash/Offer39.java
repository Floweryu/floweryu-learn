package code.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/22
 */
public class Offer39 {
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int halfLength = nums.length / 2;
        for (int num : nums) {
            Integer times = map.getOrDefault(num, 0);
            // 这里使用++times是为了让times先+1
            map.put(num, ++times);
            // 先把计数加1后再比较
            if (times > halfLength) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,2,2,2,5,4,2};
        majorityElement(arr);
    }
}
