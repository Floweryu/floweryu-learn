package code.hot;

import java.util.HashMap;

/**
 * @author Floweryu
 * @date 2024/2/26 17:50:01
 */
public class Hot560 {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        int pre = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                ans += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return ans;
    }
}
