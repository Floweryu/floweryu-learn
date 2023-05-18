package code.stack;

import java.util.Stack;

/**
 * 剑指 Offer II 036. 后缀表达式
 * @author: zhangjunfeng
 * @createTime: 2023/05/18
 */
public class Offer36II {
    public int evalRPN(String[] tokens) {
        // 数字入栈
        Stack<Integer> val = new Stack<>();
        for (String str : tokens) {
            // 如果是数字则直接入栈
            if (isNumber(str)) {
                val.push(Integer.parseInt(str));
            } else {
                // 遇到操作数则出栈两个元素
                Integer v1 = val.pop();
                Integer v2 = val.pop();
                // 记录结果值
                int res = 0;
                // 注意计算对于/和-来说是v2在前，v1在后，因为v2先入栈
                switch (str) {
                    case "+":
                        res = v2 + v1;
                        break;
                    case "-":
                        res = v2 - v1;
                        break;
                    case "*":
                        res = v2 * v1;
                        break;
                    case "/":
                        res = v2 / v1;
                        break;
                    default:
                }
                val.push(res);
            }
        }
        return val.pop();
    }

    public boolean isNumber(String str) {
        return !("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str));
    }

    public static void main(String[] args) {
        String[] token = new String[]{"4","13","5","/","+"};
        Offer36II obj = new Offer36II();
        obj.evalRPN(token);
    }
}
