package code.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 079. 所有子集
 * https://leetcode.cn/problems/TVdhkn/?envType=study-plan-v2&envId=coding-interviews-special
 * <p>
 * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 思路一：
 * 对于数组nums，设其长度为len，则一定有 2^len 个子集；每个元素在子集中都有两种状态: 1-在，0-不在
 * 所以可以初始化2^len个0/1序列。遍历每个序列确定每个位置元素是否在来获取不同的子集。
 * 例如：nums=[1,2,3]
 * [0,0,0]  ->  []
 * [1,0,0]  ->  [1]
 * [0,1,0]  ->  [2]
 * [0,0,1]  ->  [3]
 * [1,1,0]  ->  [1,2]
 * [1,0,1]  ->  [1,3]
 * [0,1,1]  ->  [2,3]
 * [1,1,1]  ->  [1,2,3]
 * @author: zhangjunfeng
 * @createTime: 2023/07/11
 */
public class Offer79II {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        // 初始化2^len个序列
        for (int i = 0; i < 1 << len; i++) {
            List<Integer> tmp = new ArrayList<>();
            // 针对每个序列都是一种结果
            for (int j = 0; j < len; j++) {
                // 如果该序位置是1，则添加到子集中
                // 例如len=3，
                // 1 << 0 -> 1
                // 1 << 1 -> 10
                // 1 << 2 -> 100
                // 用(1 << j)和i相与就可以得到序列中哪些位置是1了
                if ((i & (1 << j)) != 0 ){
                    tmp.add(nums[j]);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets2(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        dfs(nums, 0, tmp);
        return res;
    }

    /**
     * 求子集模版一
     */
    private void dfs(int[] nums, int index, List<Integer> tmp) {
        if (index == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        // 选第index个元素
        tmp.add(nums[index]);
        dfs(nums, index + 1, tmp);
        // 恢复现场
        tmp.remove(tmp.size() - 1);
        dfs(nums, index + 1, tmp);
    }

    /**
     * 求子集模板二
     */
    private void dfs2(int[] nums, int index, List<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        if (index == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            tmp.add(nums[i]);
            dfs(nums, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Offer79II offer79II = new Offer79II();
        offer79II.subsets2(new int[]{1, 2, 3});
    }

}
