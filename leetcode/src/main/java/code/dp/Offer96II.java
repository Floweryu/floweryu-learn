package code.dp;

/**
 * 剑指 Offer II 096. 字符串交织
 * https://leetcode.cn/problems/IY6buf/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/06/12
 */
public class Offer96II {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        // dp[i][j]含义：s1的前i个字符和s2的前j个字符是否能交织成s3前i+j-1个字符
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        // 设置初始值，因为s1[0],s2[0]不一定能组成s3[0]，所以下面遍历从下标1开始
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                int k = i + j - 1;
                // 矩阵按行从左到右更新，每次更新仅需本行当前更新元素的前一个元素及前一行的同列元素
                // 即每次更新只需要矩阵左侧和上侧元素，所以可以转为一维数组
                if (i > 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(k);
                }
                if (j > 0) {
                    // 这里需要判断s1是否满足, 所以要或一下dp[i][j]，表示s1或s2有一个满足条件即可
                    dp[i][j] = dp[i][j] || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(k);
                }
            }
        }
        return dp[len1][len2];
    }
    public boolean isInterleave2(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        boolean[] dp = new boolean[len2 + 1];
        dp[0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                int k = i + j - 1;
                if (i > 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(k);
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(k));
                }
            }
        }
        return dp[len2];
    }


    public static void main(String[] args) {
        Offer96II offer96II = new Offer96II();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        boolean interleave = offer96II.isInterleave2(s1, s2, s3);
        System.out.println(interleave);
    }
}
