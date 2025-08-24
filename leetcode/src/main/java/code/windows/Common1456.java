package code.windows;

/**
 * @Author zhangjunfeng
 * @Date 2025/3/2 16:24
 */
public class Common1456 {
    public int maxVowels(String s, int k) {
        int ans = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u') {
                count++;
            }
            if (i + 1 < k) {
                continue;
            }

            ans = Math.max(ans, count);
            // 出窗口
            char out = s.charAt(i - k + 1);
            if (out == 'a' || out == 'e' || out == 'i' || out == 'o' || out == 'u') {
                count--;
            }
        }
        return ans;
    }
}
