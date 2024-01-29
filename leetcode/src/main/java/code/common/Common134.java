package code.common;

/**
 * 134. 加油站
 * https://leetcode.cn/problems/gas-station/description/?envType=study-plan-v2&envId=top-interview-150
 * 贪心算法
 * 1. 遍历找寻gas-cost的最低点，因为题目已经说明有解（如果最低点都大于0，那么肯定有解）
 * 2. 找到最低点后，解就是最低点的下一点（前面已经是最低的，所以后面一定会把前面的油补回来）
 * @author: zhangjunfeng
 * @date: 2024/01/29
 */
public class Common134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int sum = 0;
        int min = 0;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            sum += gas[i] - cost[i];
            if (sum < min) {
                min = sum;
                ans = i + 1;
            }
        }
        return sum < 0 ? -1 : ans;
    }
}
