package code.stack;

import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/15
 */
public class MinStack {
    // 正常元素栈
    private Stack<Integer> stack;

    // 存放最小值的栈
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        // 如果最小值栈为空或者新入栈的值x比最小值栈顶元素小，将x入站
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        int pop = stack.pop();
        // 如果出栈元素是最小值元素，则minStack也需要出栈删除该最小值
        if (minStack.peek() == pop) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
