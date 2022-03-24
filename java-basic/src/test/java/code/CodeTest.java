package code;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Floweryu
 * @date 2022/3/13 22:05
 */
public class CodeTest {

    /**
     * 剑指Offer.48
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            while (set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            set.add(c);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int i = 0, j = 0, k = 0;
        
        for (int index = 1; index < n; index++) {
            // 在生成的三个数组中选最小的丑数
            int mini = Math.min(res[i] * 2, Math.min(res[j] * 3, res[k] * 5));
            
            // 三个数组都有可能存在最小的元素, 只要是最小的, 就需要移动指针
            if (res[i] * 2 == mini) i++;
            if (res[j] * 3 == mini) j++;
            if (res[k] * 5 == mini) k++;
            
            res[index] = mini;
        }
        
        return res[n - 1];
    }

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        // mini记录prices的最小值
        int maxn = 0, mini = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 遇到比mini小的, 替换mini值
            if (prices[i] <= mini) {
                mini = prices[i];
            } else { // 遇到比mini大的, 计算差值
                maxn = Math.max(maxn, prices[i] - mini);
            }
        }
        return maxn;
    }

    /**
     * No.2044
     * @url https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/
     */
    public int countMaxOrSubsets(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max |= num;
        }
        return dfs(0, nums, 0, max);
    }
    
    private int dfs(int curIndex, int[] nums, int curValue, int max) {
        if (curIndex == nums.length - 1) {
            return curValue == max ? 1 : 0;
        }
        // 
        return dfs(curIndex + 1, nums, curValue | nums[curIndex], max) + dfs(curIndex + 1, nums, curValue, max);
    }

    /**
     * LeetCode.661
     * @param img
     * @return
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = calc(i, j, m, n, img);
            }
        }
        return res;
    }
    
    private int calc(int i, int j, int m, int n, int[][] img) {
        int sum = 0, count = 0;
        for (int a = i - 1; a <= i + 1; a++) {
            for (int b = j - 1; b <= j + 1; b++) {
                if (a >= 0 && a < m && b >= 0 && b < n) {
                    sum += img[a][b];
                    count++;
                }
            }
        }
        return sum / count;
    }
    
    @Test
    public void codeTest() {
        
        int res1 = nthUglyNumber(10);
        System.out.println(res1);
    }
}
