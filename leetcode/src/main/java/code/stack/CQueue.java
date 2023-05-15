package code.stack;

import java.util.Stack;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 * https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/description/
 * @author: zhangjunfeng
 * @createTime: 2023/05/15
 */
public class CQueue {

    // 输出栈，出队使用
    private Stack<Integer> outStack;
    // 输入栈，当做进入队列使用
    private Stack<Integer> inStack;
    public CQueue() {
        outStack = new Stack<>();
        inStack = new Stack<>();
    }

    // 入队直接操作入栈即可
    public void appendTail(int value) {
        inStack.push(value);
    }

    /**
     * 出队时, 如果outStack为空，则把inStack里面元素pop出放入outStack中
     * 如果outStack不为空，则说明上一次队列元素还没有完全出栈
     * 如果inStack和outStack都为空，说明队内没有元素
     */
    public int deleteHead() {
        if (outStack.isEmpty()) {
            if (inStack.isEmpty()) {
                return -1;
            }
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }
}
