package code.offer;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * @author zhangjunfeng
 * @date 2023/3/20 19:59
 */
public class Common51 {
    int res = 0;
    public int reversePairs(int[] nums) {
        this.res = 0;
        mergeSort(nums, 0, nums.length - 1);
        return res;
    }
    
    public void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }
    
    public void merge(int[] nums, int left, int mid, int right) {
        int i = left, j = mid + 1, k = 0;
        int[] tmp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                tmp[k++] = nums[j++];
                // 只有此处修改(参考归并排序)
                // 右边当前值比左边当前值都要小，则右边当前值对于左边剩余部分的mid-i+1个数都是逆序
                res += (mid - i + 1);
            } else {
                // 左边当前值小于右边当前值, 不是逆序
                tmp[k++] = nums[i++];
            }
        }
        
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        
        // 将nums的left到right这段赋值为有序的
        for(int t = 0; t < tmp.length; t++){
            nums[left + t] = tmp[t];
        }
    }
}
