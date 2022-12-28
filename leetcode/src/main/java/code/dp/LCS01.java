package code.dp;

/**
 * @author zhangjunfeng
 * @date 2022/12/27 19:38
 */
public class LCS01 {
    public int leastMinutes(int n) {
        // 最开始每分钟下载个数
        int cur = 1;
        int time = 0;
        while (cur < n) {
            // 先扩带宽, 将带宽扩大到比n大
            cur *= 2;
            time++;
        }
        // 最后花费一分钟下载
        return time + 1;
    }
    
    /**
     * 动态规划
     * @param n
     * @return
     */
    public static int leastMinutes2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        // 分钟: 1 2 3 4
        // 个数: 2 4 8 16
        // 为什么要(i + 1)? 假设n=3, 不加1则是1, dp[1]=1的时候下载带宽并不够, 所以需要+1
        // 最后+1为什么? 前面都是扩容带宽, 最后一分钟下载
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[(i + 1) / 2] + 1;
        }
        return dp[n];
    }
    
    public static void main(String[] args) {
        int i = leastMinutes2(9);
        System.out.println(i);
    }
}
