package code.dp;

/**
 * 剑指 Offer II 090. 环形房屋偷盗
 * https://leetcode.cn/problems/PzWKhm/?envType=study-plan-v2&envId=coding-interviews-special
 * 思考：
 * 由于相邻房屋不能偷窃，又由于房屋是环形的，所以有以下规则
 * 1. 偷窃了第一间房屋则不能偷窃最后一间，此时偷窃的房屋范围是[0, length - 2]
 * 2. 偷窃了最后一间房屋则不能偷窃第一间，此时偷窃的房屋范围是[1, length - 1]
 * 所以，分别寻找上面两个范围的偷窃最大值，然后再取两个范围最大值即为结果
 * 寻找单个区间范围最大值思路和房屋偷盗思路一样，只不过不用数据，而是用变量来记录最大值
 * @author: zhangjunfeng
 * @createTime: 2023/05/30
 */
public class Offer90II {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robDp(nums, 0, nums.length - 1), robDp(nums, 1, nums.length));
    }

    public int robDp(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        /**
         * 假设nums如下： 1     2    3     3  4
         *              fir  sec   i
         *              每次second和nums[i]+first
         */
        for (int i = start + 2; i < end; i++) {
            int tmp = second;
            second = Math.max(nums[i] + first, second);
            first = tmp;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0};
        Offer90II offer90II = new Offer90II();
        int rob = offer90II.rob(arr);
    }
}
