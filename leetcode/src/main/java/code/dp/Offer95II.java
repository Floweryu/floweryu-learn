package code.dp;

/**
 * 剑指 Offer II 095. 最长公共子序列
 * https://leetcode.cn/problems/qJnOS7/
 * dp方程如下：
 * 如果遇到相等字符，则dp[i][j] = dp[i-1][j-1] + 1
 * 如果字符不相等，则dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) 这样就能将前面相等字符状态进行转移
 * @author: zhangjunfeng
 * @createTime: 2023/05/29
 */
public class Offer95II {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        // 定义dp数组： 这里特殊处理，由于后面需要判断i-1和j-1，所以dp[0][0]、dp[0][j]、dp[i][0]需要预留，防止为空
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
