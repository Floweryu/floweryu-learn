package code.binary;

/**
 * 4. 寻找两个正序数组的中位数
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 * @author zhangjunfeng
 * @date 2023/3/13 19:47
 */
public class Search4 {
    
    /**
     * 1. 迭代核心是找排序为第k的数（剩余总序列里），然后剪掉前`k/2`个数。 （剔除前`k/2`个较小的后，在剩下的里面找第`k-(k/2)`个，即最开始想要的第`k`个，然后继续迭代这个过程）
     * 2. 终结的条件为找第`k=1`个**or**剪到边剪得啥都不剩了。 （k=1说明找第一小的数，返回最小值即可。剪的啥都不剩了说明一个数组完全大于另一个，直接返回中位数即可）
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 按照下面思路: left + right 可以满足奇数和偶数所有情况
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (fingKth(nums1, 0, nums2, 0, left) + fingKth(nums1, 0, nums2, 0, right)) / 2.0;
    }
    
    public int fingKth(int[] nums1, int i, int[] nums2, int j, int k) {
        // nums1为空数组
        if (nums1.length <= i) return nums2[j + k - 1];
        // nums2为空数组
        if (nums2.length <= j) return nums1[i + k - 1];
        // k==1说明找第一小的数(剩余序列)
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        // k: 本次两个数组的中间下标
        // i + k/2 - 1: k/2是因为现在在单个数组中, 该步骤表示nums1中有没有第k/2个数
        // 为什么赋最大值？
        // 假如nums1长度为2, nums2长度为12, 则k为(2+12)/2=7, k/2=3
        // 因为nums1长度小于3, 则无法判断中位数是否在nums1中; 而nums2中前3个肯定不是中位数
        // 所以当k/2不存在时,将其设置为最大值,这样可以保留继续下一次循环
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            // 说明nums1的前i+k/2个数都被舍弃, 因为在整体数组前半部分比midVal2小, 说明中位数可能在nums2中
            // 下一轮的中位数下标要在舍弃的基础上计算: k/2是舍弃部分, 所以是: k - k / 2
            return fingKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return fingKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
    
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 先归并排序, 因为两个都是有序数组
        int[] res = mergeSort(nums1, nums2);
        // 这里使用一个小trick, 不用判断奇偶长度
        return (res[(res.length + 1) / 2 - 1] + res[(res.length + 2) / 2 - 1]) / 2.0;
    }
    
    // 归并排序底层实现: 合并两个有序数组
    public int[] mergeSort(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 < 1) return nums2;
        if (len2 < 1) return nums1;
        int[] res = new int[len1 + len2];
        int i = 0, j = 0, index = 0;
        while (i < len1 && j < len2) {
            res[index++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < len1) {
            res[index++] = nums1[i++];
        }
        while (j < len2) {
            res[index++] = nums2[j++];
        }
        return res;
    }
    
    public static void main(String[] args) {
        Search4 search4 = new Search4();
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2, 3, 4};
        double medianSortedArrays1 = search4.findMedianSortedArrays1(nums1, nums2);
        System.out.println(medianSortedArrays1);
    }
}
