package code.bit;

/**
 * 剑指 Offer II 004. 只出现一次的数字
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * @author Floweryu
 * @date 2023/5/23 22:05
 */
public class Offer4II {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                // 将数组第i位相加
                sum += (num >> i) & 1;
            }
            // 如果第i位相加之和不等于0，说明是答案的第i位不起作用（因为其他元素都是3个，第i位相加后除以3余数是0才对）
            if (sum % 3 != 0) {
                // 1<<i表示当前余数是第i位的余数。
                // | 表示将和累加
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
