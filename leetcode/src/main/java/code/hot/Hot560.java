package code.hot;

import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * @author Floweryu
 * @date 2024/2/26 17:50:01
 */
public class Hot560 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // 将比nums[i]小的移出队列
            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
                deque.removeLast();
            }

            // 将i添加进队列
            deque.addLast(i);

            // 如果队列第一个元素在窗口外，则移除
            if (deque.getFirst() < i + 1 - k) {
                deque.removeFirst();
            }

            // 获取窗口最大值并保存
            if (i + 1 - k >= 0) {
                ans[i + 1 - k] = nums[deque.getFirst()];
            }
        }
        return ans;
    }
}
