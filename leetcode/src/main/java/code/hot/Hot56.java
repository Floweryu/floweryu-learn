package code.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Floweryu
 * @date 2024/2/28 19:06:00
 */
public class Hot56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            int[] pre = ans.get(ans.size() - 1);
            if (cur[0] <= pre[1]) {
                pre[1] = Math.max(cur[1], pre[1]);
            } else {
                ans.add(cur);
            }
        }
        return ans.toArray(int[][]::new);
    }
}
