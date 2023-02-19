package code.daily;

/**
 * @author Floweryu
 * @date 2022/12/21 14:22
 */
public class LeetCode1753 {
    
    public int maximumScore(int a, int b, int c) {
        int sum = a + b + c;
        int maxVal = Math.max(Math.max(a, b), c);
        // 若a < b < c; 
        // 这时如果a + b <= c, 则a, b 肯定能变为0, 所以此时是a + b 也就是(sum - c)
        // 如果a + b > c, 则a, b 与c匹配, 假设a匹配k1, b匹配k2, 则k1 + k2 = c. 剩下的[(a-k1) + (b-k2)] / 2, 相加即 (a + b + c) / 2;
        return Math.min(sum - maxVal, sum / 2);
    }
}
