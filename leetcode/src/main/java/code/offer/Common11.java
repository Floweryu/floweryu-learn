package code.offer;

/**
 * [3,4,5,1,2]
 * 
 * @author zhangjunfeng
 * @date 2023/3/21 12:59
 */
public class Common11 {
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 为什么以right处元素为参考？
            // right处元素一定小于或等于0位置元素, 比right处还小的元素一定在最小值和right处元素之间，比right处大的元素一定在0到最小值之间
            if (numbers[mid] < numbers[right]) {
                // 说明nums[mid]在最小值右侧, 则right-mid这部分可以丢弃
                right = mid;
            } else if (numbers[mid] > numbers[right]){
                // 说明nums[mid]在最小值左侧, 则left~mid这部分可以丢弃
                left = mid + 1;
            } else {
                // 如果相等，则不能确定在最小值左侧还是右侧，但是可以忽略这个相等值
                right -= 1;
            }
        }
        return numbers[right];
    }
}
