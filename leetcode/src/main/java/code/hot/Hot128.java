package code.hot;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * https://leetcode.cn/problems/longest-consecutive-sequence/description/?envType=study-plan-v2&envId=top-100-liked
 * [100,4,200,1,3,2]
 */
public class Hot128 {

    /**
     * 暴力
     * 遍历每个num，判断num+1是否在nums中，为了方便可以将nums放入set中
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int ans = 0;
        for (int num : nums) {
            if (set.contains(num - 1)) {
                continue;
            }

            int tmp = num;
            while (set.contains(tmp + 1)) {
                tmp++;
            }
            ans = Math.max(ans, tmp - num + 1);
        }
        return ans;
    }
}
