package code.daily;

import java.util.Arrays;

/**
 * @author Floweryu
 * @date 2022/12/20 18:44
 */
public class LeetCode1760 {
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1;
        int right = Arrays.stream(nums).max().getAsInt();
        
        int res = 0;
        while (left <= right) {
            int y = (left + right) / 2;
            
            // 计算每个袋子中的球需要几次操作能到y
            int ops = 0;
            for (int n : nums) {
                ops += (n - 1) / y;
            }
            
            if (ops <= maxOperations) {
                // 如果满足小于最大操作次数, 则记录一下此时球袋中的值, 并减小每个袋子要装的球（越小越好）
                res = y;
                right = y - 1;
            } else {
                // 如果操作数大于最大操作数, 则增加每个袋子要装的球
                left = y + 1;
            }
        }
        return res;
    }
}
