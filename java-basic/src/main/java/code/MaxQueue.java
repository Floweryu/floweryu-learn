package code;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangjunfeng
 * @date 2022/3/14 17:21
 */
public class MaxQueue {
    
    private Queue<Integer> queue;

    /**
     * 这里使用双端队列的原因:
     * help队列从队头到队尾是从大到小的, 且数据必须在queue中存在
     * 当有新数据val入队时, 把小于val的数从队尾开始移除. 这样能保证队头一定是当前最大值
     * 如果是单向队列, 则不能保证队头元素一定是最大值  3 1 2 5 3 4
     */
    private Deque<Integer> help;
    
    public MaxQueue() {
        queue = new LinkedList<>();
        help = new ArrayDeque<>();
    }

    public int max_value() {
        return help.isEmpty() ? -1 : help.peek();
    }

    public void push_back(int value) {
        queue.offer(value);
        
        while (! help.isEmpty() && help.peekLast() < value) {
            help.pollLast();
        }
        help.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int res = queue.poll();
        if (! help.isEmpty() && help.peek() == res) {
            help.poll();
        }
        return res;
    }
}
