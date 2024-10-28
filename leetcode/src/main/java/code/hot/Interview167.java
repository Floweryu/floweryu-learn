package code.hot;

/**
 * @Author Floweryu
 * @Date 2024/7/4 22:25
 */
public class Interview167 {
    public int maxArea(int[] height) {
        int length = height.length;
        int i = 0, j = length;
        int ans = 0;
        while (i < j) {
            int cur = (j - i) * Math.min(height[i], height[j]);
            if (cur < ans) {
                if (height[i] < height[j]) {
                    i++;
                } else {
                    j--;
                }
            } else {
                ans = cur;
                if (height[i] < height[j]) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return ans;
    }
}
