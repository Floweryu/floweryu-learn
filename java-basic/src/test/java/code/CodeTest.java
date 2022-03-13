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
    
    @Test
    public void codeTest() {
        
        int res1 = nthUglyNumber(10);
        System.out.println(res1);
    }
}
