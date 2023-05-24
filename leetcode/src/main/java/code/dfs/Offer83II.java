package code.dfs;

import java.util.*;

/**
 * 剑指 Offer II 083. 没有重复元素集合的全排列
 * https://leetcode.cn/problems/VvJkup/description/
 * @author: zhangjunfeng
 * @createTime: 2023/05/24
 */
public class Offer83II {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        for (int num : nums) {
            tmp.add(num);
        }
        backTrack(res, tmp, nums.length - 1, 0);
        return res;
    }

    public void backTrack(List<List<Integer>> res, List<Integer> tmp, int n, int first) {
        if (first == n) {
            // 这里需要拷贝一份，否则后面修改会改变tmp数组的值
            res.add(new ArrayList<>(tmp));
        } else {
            for (int i = first; i <= n; i++) {
                Collections.swap(tmp, first, i);
                backTrack(res, tmp, n, first + 1);
                // 这里需要还原现场，否则first位只交换一次
                Collections.swap(tmp, first, i);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Offer83II offer83II = new Offer83II();
        List<List<Integer>> permute = offer83II.permute(nums);
        System.out.println(permute);
    }
}
