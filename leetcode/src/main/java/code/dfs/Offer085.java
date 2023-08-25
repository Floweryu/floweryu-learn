package code.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * LCR 085. 括号生成
 * 正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * @author: zhangjunfeng
 * @createTime: 2023/08/25
 */
public class Offer085 {

    private List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        backTrack(n, new StringBuilder(), 0, 0);
        return ans;
    }

    public void backTrack(int n, StringBuilder sb, int open, int close) {
        if (sb.length() == 2 * n) {
            ans.add(sb.toString());
            return;
        }
        if (open < n) {
            // 选择添加(
            sb.append("(");
            backTrack(n, sb, open + 1, close);
            // 回退
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            // 选择添加)
            sb.append(")");
            backTrack(n, sb, open, close + 1);
            // 回退到上一层
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
