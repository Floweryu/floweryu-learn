package code.common;

/**
 * 45. 跳跃游戏 II
 * https://leetcode.cn/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-interview-150
 * 贪心算法
 * @author: zhangjunfeng
 * @date: 2024/01/11
 */
public class Common45 {
    public int jump(int[] nums) {
        // 需要调到的位置
        int position = nums.length - 1;
        int step = 0;
        // 贪心算法
        // 每次都寻找距离终点最远的点
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    step++;
                    // 第二次for循环是从0开始的，找到一个点能到达终点即说明该点是举例终点最远距离
                    break;
                }
            }
        }
        return step;
    }

    public int jump2(int[] nums) {
        // 最远能跳到的位置
        int maxPosition = 0;
        // 记录当前能跳跃到的最远位置
        int end = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 如果当前位置到最远位置处, 说明可以进行下一次跳跃
            if (i == end) {
                // 更新最远位置
                end = maxPosition;
                step++;
            }
        }
        return step;
    }
}
