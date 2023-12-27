package code.common;

/**
 * @author: zhangjunfeng
 * @date: 2023/12/27
 */
public class Common88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m + n];
        int len1 = m;
        for (int i = 0; i < m + n - 1; i++) {
            if (nums1[i] > nums1[i + 1]) {
                len1 = i + 1;
                break;
            }
        }
        int i = 0, j = 0, index = 0;
        while (i < len1 && j < n) {
            if (nums1[i] <= nums2[j]) {
                res[index++] = nums1[i++];
            } else {
                res[index++] = nums2[j++];
            }
        }

        while (i < len1) {
            res[index++] = nums1[i];
            i++;
        }
        while (j < n) {
            res[index++] = nums2[j];
            j++;
        }
        i = 0;
        while (i < m + n) {
            nums1[i] = res[i++];
        }
    }
}
