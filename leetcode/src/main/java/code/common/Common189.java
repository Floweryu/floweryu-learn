package code.common;

/**
 * @author: zhangjunfeng
 * @date: 2023/12/28
 */
public class Common189 {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[(i + k) % len] = nums[i];
        }
        System.arraycopy(arr, 0, nums, 0, len);
    }

    public static void main(String[] args) {
        Common189 common189 = new Common189();
        int[] arr = {1,2,3};
        common189.rotate(arr, 4);
    }
}
