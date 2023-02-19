package code.dynamic;

/**
 * @author Floweryu
 * @date 2022/12/6 21:41
 */
public class LeetCode1137 {
    public int tribonacci(int n) {
        if (n <= 1) {
            return n;
        }
        if (n == 2) {
            return 1;
        }
        
        int[] res = new int[n + 1];
        res[0] = 0; res[1] = 1; res[2] = 1;
        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2] + res[i - 3];
        }
        return res[n];
    }
}
