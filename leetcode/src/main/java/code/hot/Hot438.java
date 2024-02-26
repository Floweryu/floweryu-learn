package code.hot;

import java.util.*;

/**
 * s = "cbaebabacd", p = "abc"
 * @author Floweryu
 * @date 2024/2/23 15:27:41
 */
public class Hot438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] count = new int[26];
        // 统计每个字符个数
        for (int i = 0; i < p.length(); i++) {
            count[p.charAt(i) - 'a']++;
        }

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            // 遇到字符在p里面，接着循环遍历
            count[s.charAt(r) - 'a']--;
            // 遇到字符不在p里面，将从l开始的字符重新计算一遍个数（因为上一步为判断将p字符数量都变成0）
            // 下面循环主要移动l指针，寻找下一个异位词开始点，并将异位词个数恢复
            while (count[s.charAt(r) - 'a'] < 0) {
                count[s.charAt(l) - 'a']++;
                l++;
            }
            if (r - l + 1 == p.length()) {
                ans.add(l);
            }
        }
        return ans;
    }
}
