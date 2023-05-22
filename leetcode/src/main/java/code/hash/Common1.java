package code.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * https://leetcode.cn/problems/two-sum/description/
 * 构造一个map：key-数值, value-数值在数组中的下标
 * 遍历数组进行下面判断：
 * - 如果target-nums[i]在map中，则说明找到符合条件的两个元素，直接返回两个元素下标即可
 * - 如果target-nums[i]不在map中，将当前元素及下标放入map
 * - 最后如果不存在结果，返回空数组
 * @author: zhangjunfeng
 * @createTime: 2023/05/22
 */
public class Common1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
