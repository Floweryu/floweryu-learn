package code.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 084. 含有重复元素集合的全排列
 * https://leetcode.cn/problems/7p8L0Z/
 * @author: zhangjunfeng
 * @createTime: 2023/05/24
 */
public class Offer84II {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        // 先把数组排序，这样在回溯中可以防止重复元素
        Arrays.sort(nums);
        // 递归过程中标记该位置元素是否已经使用过
        boolean[] visited = new boolean[nums.length];
        // idx：当前填充的元素位置
        backTrack(nums, res, tmp, visited, 0);
        return res;
    }

    public void backTrack(int[] nums, List<List<Integer>> res, List<Integer> tmp, boolean[] visited, int idx) {
        // 此时说明得到一个结果
        if (idx == nums.length) {
            // 拷贝一份是因为tmp在递归过程中可能会变
            res.add(new ArrayList<>(tmp));
        } else {
            for (int i = 0; i < nums.length; i++) {
                // 如果i位置元素已经被访问到 或者 nums[i] == nums[i - 1]并且前一个数未被访问 直接跳过
                // 第二个条件：保证每次填入的数是从左到右第一个未被填入的数字
                if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                    continue;
                }
                // 将通过条件的元素添加
                tmp.add(nums[i]);
                // 标记已访问
                visited[i] = true;
                backTrack(nums, res, tmp, visited, idx + 1);
                // 回溯嘛，，需要还原现场，比如获取到一个数组[1,2,3], 还有[1,3,2]，这个2后面可能再用
                visited[i] = false;
                tmp.remove(idx);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3};
        Offer84II offer84II = new Offer84II();
        List<List<Integer>> permute = offer84II.permuteUnique(nums);
        System.out.println(permute);
    }
}
