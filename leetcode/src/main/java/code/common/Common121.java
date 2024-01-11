package code.common;

/**
 * 121. 买卖股票的最佳时机
 * @author: zhangjunfeng
 * @date: 2024/01/11
 */
public class Common121 {
    public int maxProfit(int[] prices) {
        int[] minPrice = new int[prices.length + 1];
        minPrice[0] = prices[0];
        int maxValue = 0;
        for (int i = 1; i < prices.length; i++) {
            // 计算得到当前i时的最小买入价格
            minPrice[i] = Math.min(prices[i], minPrice[i - 1]);
            // 计算最大利润
            maxValue = Math.max(prices[i] - minPrice[i], maxValue);
        }
        return maxValue;
    }
}
