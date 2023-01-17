package code.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/description/
 * @author zhangjunfeng
 * @date 2023/1/17 16:40
 */
public class Dp22 {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), 0, 0, n);
        return res;
    }
    
    private static void dfs(List<String> res, StringBuilder cur, int open, int close, int n) {
        if (cur.length() == 2 * n) {
            res.add(cur.toString());
            return;
        }
        
        // 先添加左括号
        if (open < n) {
            cur.append("(");
            dfs(res, cur, open + 1, close, n);
            // 删除完右括号后, 会退出到这里, 删除一个左括号
            // 然后又进入dfs递归, 这时close < open, 就会添加), 即(()
            cur.deleteCharAt(cur.length() - 1);
        }
        
        // 左括号满后, 添加对应数量的右括号
        if (close < open) {
            cur.append(")");
            dfs(res, cur, open, close + 1, n);
            // 右括号满后, 会从这里开始出栈, 逐步删除右括号
            cur.deleteCharAt(cur.length() - 1);
        }
    }
    
    public static void main(String[] args) {
        List<String> strings = generateParenthesis(3);
        System.out.println(strings);
    }
}
