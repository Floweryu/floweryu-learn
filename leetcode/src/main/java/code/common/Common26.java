package code.common;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: zhangjunfeng
 * @date: 2023/12/27
 */
public class Common26 {
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                nums[index++] = nums[i];
                set.add(nums[i]);
            }
        }
        return index;
    }
}
