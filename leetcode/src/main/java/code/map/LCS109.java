package code.map;

import java.util.*;

/**
 * LCR 109. 打开转盘锁
 * https://leetcode.cn/problems/zlDJc7/description/?envType=study-plan-v2&envId=coding-interviews-special
 * @author: zhangjunfeng
 * @createTime: 2023/09/05
 */
public class LCS109 {

    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        // 转为set, 降低后续时间复杂度
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> used = new HashSet<>();
        queue.offer("0000");
        used.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            // 先预设走一步，下面都是对走的这一步不同的选择
            ++step;
            int size = queue.size();
            // 下面都是对这一步的不同选择
            for (int i = 0; i < size; i++) {
                String val = queue.poll();
                // 遍历下一个状态
                for (String next : nextStatus(val)) {
                    // 如果不在死锁名单或者没有标记过
                    if (!deadSet.contains(next) && !used.contains(next)) {
                        // 如果遇到目标值，直接返回
                        if (next.equals(target)) {
                            return step;
                        }
                        queue.offer(next);
                        used.add(next);
                    }
                }
            }

        }
        return -1;
    }

    private List<String> nextStatus(final String status) {
        // 计算下一个状态的值
        List<String> res = new ArrayList<>();
        char[] array = status.toCharArray();
        // 四个位置
        for (int i = 0; i < 4; i++) {
            // 保留该位置
            char ch = array[i];
            array[i] = nextChar(ch);
            res.add(String.valueOf(array));
            array[i] = preChar(ch);
            res.add(String.valueOf(array));
            // 恢复该位置
            array[i] = ch;
        }
        return res;
    }

    private char nextChar(final char ch) {
        return ch == '9' ? '0' : (char) (ch + 1);
    }

    private char preChar(final char ch) {
        return ch == '0' ? '9' : (char) (ch - 1);
    }
}
