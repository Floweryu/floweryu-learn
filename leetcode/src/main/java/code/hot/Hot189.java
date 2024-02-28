package code.hot;

/**
 * @author Floweryu
 * @date 2024/2/28 19:27:00
 */
public class Hot189 {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[len];
        int preLen = len - k;
        for (int i = 0; i < len; i++) {
            ans[i] = nums[(i + preLen) % 7];
        }
        System.arraycopy(ans, 0, nums, 0, len);
    }

    public static void main(String[] args) {
        Hot189 hot189 = new Hot189();
        hot189.rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }
}
