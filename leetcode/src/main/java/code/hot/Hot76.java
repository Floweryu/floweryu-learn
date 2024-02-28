package code.hot;

/**
 * @author Floweryu
 * @date 2024/2/27 20:33:22
 */
public class Hot76 {
    public String minWindow(String s, String t) {
        int[] cnt = new int[128];
        for (int i = 0; i < t.length(); i++) {
            cnt[t.charAt(i)]++;
        }
        int l = 0, r = 0, valid = t.length(), ans = Integer.MAX_VALUE, minL = 0, minR = 0;
        while (r < s.length()) {
            // 如果当前窗口包含字符
            if (cnt[s.charAt(r)]-- > 0) {
                valid--;
            }
            r++;

            // valid=0说明当前窗口包含所有字符
            while (valid == 0) {
                if (r - l < ans) {
                    minL = l;
                    minR = r;
                    ans = minR - minL;
                }
                // 移动左边界，直到遇见t中字符
                // 解释一下这里为什么是等于0，因为前面进来时判断t字符已经减1，如果这里+1刚好等于0则说明是t字符
                if (cnt[s.charAt(l)]++ == 0) {
                    valid++;
                }
                l++;
            }
        }
        return ans == Integer.MAX_VALUE ? "" : s.substring(minL, minR);
    }

    public String minWindow2(String s, String t) {
        int[] cnt = new int[128];
        for (int i = 0; i < t.length(); i++) cnt[t.charAt(i)]++;
        int l = 0, r = 0, ansL = 0, ansR = 0, ans = Integer.MAX_VALUE, cntT = t.length();
        while (r < s.length()) {
            if (cnt[s.charAt(r++)]-- > 0) {
                cntT--;
            }
            while (cntT == 0) {
                if (r - l < ans) {
                    ans = r - l;
                    ansL = l;
                    ansR = r;
                }
                if (cnt[s.charAt(l++)]++ == 0) cntT++;
            }
        }
        return ans == Integer.MAX_VALUE ? "" : s.substring(ansL, ansR);
    }

    public static void main(String[] args) {
        Hot76 hot76 = new Hot76();
        String s = "ADOBECODEBANC", t = "ABC";
        String s1 = hot76.minWindow2(s, t);
    }
}
