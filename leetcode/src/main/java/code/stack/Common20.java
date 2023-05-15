package code.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * https://leetcode.cn/problems/valid-parentheses/
 * @author: zhangjunfeng
 * @createTime: 2023/05/15
 */
public class Common20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                // 如果执行到此处, stack是空, 则说明肯定是]})其中之一, 前面没有[{(, 就不能匹配
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (map.get(top) != ch) {
                    return false;
                }
            }
        }
        // 最后避免只有[({的情况, 判断stack是否为空
        return stack.isEmpty();
    }
}
