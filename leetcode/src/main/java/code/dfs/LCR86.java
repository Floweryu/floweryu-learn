package code.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LCR 086. 分割回文串
 * https://leetcode.cn/problems/M99OJA/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @date: 2023/12/06
 */
public class LCR86 {

    List<List<String>> res = new ArrayList<>();
    List<String> tmp = new ArrayList<>();
    public String[][] partition(String s) {
        backTrack(s, 0);
        String[][] result = new String[res.size()][res.get(0).size()];
        for (int i = 0; i < res.size(); i++) {
            List<String> rowList = res.get(i);
            result[i] = rowList.toArray(new String[0]);
        }

        return result;
    }

    private void backTrack(String s, int index) {
        if (index == s.length()) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (check(s, index, i)) {
                tmp.add(s.substring(index, i + 1));
            } else {
                continue;
            }
            // 截止到当前位置是回文串，则继续递归
            backTrack(s, i + 1);
            // 回溯出来去掉上一次结果
            tmp.remove(tmp.size() - 1);
        }
    }

    private boolean check(String s, int start, int end) {
        // 判断当前字符串是否为回文串
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        LCR86 lcr86 = new LCR86();
        String[][] googles = lcr86.partition("google");
        for (String[] row : googles) {
            System.out.println(Arrays.toString(row));
        }
    }
}
