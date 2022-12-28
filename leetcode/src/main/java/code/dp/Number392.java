package code.dp;

/**
 * @author zhangjunfeng
 * @date 2022/11/15 20:37
 */
public class Number392 {
    public static boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }
    
    public static void main(String[] args) {
        boolean subsequence = isSubsequence("abc", "ahbgdcd");
        System.out.println(subsequence);
    }
}
