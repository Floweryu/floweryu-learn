package code.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * https://leetcode.cn/problems/generate-parentheses/
 * @author Floweryu
 * @date 2023/5/28 13:09
 */
public class Common22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, n, new StringBuilder(), 0, 0);
        return res;
    }

    public void dfs(List<String> res, int n, StringBuilder sb, int open, int close) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        // 先添加左括号
        if (open < n) {
            sb.append("(");
            dfs(res, n, sb, open + 1, close);
            // 删除完右括号后, 会退出到这里, 删除一个左括号
            // 然后又进入dfs递归, 这时close < open, 就会添加), 即(()
            sb.deleteCharAt(sb.length() - 1);
        }

        // 左括号满后, 添加对应数量的右括号
        if (close < open) {
            sb.append(")");
            dfs(res, n, sb, open, close + 1);
            // 右括号满后, 会从这里开始出栈, 逐步删除右括号
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
