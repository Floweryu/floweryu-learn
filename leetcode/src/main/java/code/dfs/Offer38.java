package code.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
 * 剑指 Offer 38. 字符串的排列
 * 题解一样：剑指 Offer II 084. 含有重复元素集合的全排列 https://leetcode.cn/problems/7p8L0Z/
 * @author: zhangjunfeng
 * @createTime: 2023/05/25
 */
public class Offer38 {
    public String[] permutation(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[s.length()];
        // 将字符串转为数组，这样就和整数数组全排列一致
        char[] chars = s.toCharArray();
        // 排序是为了后面去重
        Arrays.sort(chars);
        backTrack(res, visited, sb, chars, 0);
        return res.toArray(new String[]{});
    }

    public void backTrack(List<String> res, boolean[] visited, StringBuilder sb, char[] chars, int idx) {
        // 填充元素的下标满足个数后，获取当前结果
        if (idx == chars.length) {
            // 这里sb.toString()里面已经拷贝了一份
            res.add(sb.toString());
        } else {
            for (int i = 0; i < chars.length; i++) {
                // 如果i位置元素已经被访问到 或者 chars[i] == chars[i - 1]并且前一个数未被访问 直接跳过
                // 第二个条件：保证每次填入的数是从左到右第一个未被填入的字符
                if (visited[i] || (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1])) {
                    continue;
                }
                // 标记已经被访问
                visited[i] = true;
                sb.append(chars[i]);
                backTrack(res, visited, sb, chars, idx + 1);
                // 回溯嘛，，需要还原现场，比如获取到一个数组[1,2,3], 还有[1,3,2]，这个2后面可能再用
                sb.deleteCharAt(idx);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Offer38 offer38 = new Offer38();
        String[] abcs = offer38.permutation("abc");
        System.out.println(Arrays.toString(abcs));
    }
}
