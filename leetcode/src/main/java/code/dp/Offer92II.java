package code.dp;

/**
 * 剑指 Offer II 092. 翻转字符
 * https://leetcode.cn/problems/cyJERH/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/06/01
 */
public class Offer92II {
    public int minFlipsMonoIncr(String s) {
        // dp0表示遍历到第i个元素如果是0最小翻转次数
        // dp1表示遍历到第i个元素如果是1最小翻转次数
        int dp0 = 0, dp1 = 0;
        for (char ch : s.toCharArray()) {
            // 遍历到第i个元素时如果取1，则i-1个元素可以是1，也可以是0，所以需要取两者最小值
            dp1 = Math.min(dp1, dp0) + (ch == '0' ? 1 : 0);
            dp0 += (ch == '1' ? 1 : 0);
        }
        return Math.min(dp0, dp1);
    }
}
