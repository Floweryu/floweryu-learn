package code.common;

/**
 * 55. 跳跃游戏
 * https://leetcode.cn/problems/jump-game/submissions/494856788/?envType=study-plan-v2&envId=top-interview-150
 * @author: zhangjunfeng
 * @date: 2024/01/11
 */
public class Common55 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        // 记录可以跳跃到的最远位置
        int k = 0;
        for (int i = 0; i < len; i++) {
            // 如果能跳到的最远位置都达不到i，说明跳跃不到最后一点
            if (k < i) return false;
            // nums[i]+i：表示当前位置可以跳到的最远位置
            k = Math.max(k, nums[i] + i);
        }
        return true;
    }
}
