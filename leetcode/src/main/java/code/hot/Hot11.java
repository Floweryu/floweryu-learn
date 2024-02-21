package code.hot;

/**
 * 11. 盛最多水的容器
 * @author Floweryu
 * @date 2024/2/20 20:37:53
 */
public class Hot11 {
    public int maxArea(int[] height) {
        int ans = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            ans = height[i] > height[j] ?
                    Math.max(ans, (j - i) * height[j--]) :
                    Math.max(ans, (j - i) * height[i++]);
        }
        return ans;
    }
}
