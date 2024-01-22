package code.common;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/description/?envType=study-plan-v2&envId=top-interview-150
 * @author: zhangjunfeng
 * @date: 2024/01/22
 */
public class Common380 {

    Set<Integer> set;
    Random random;
    public Common380() {
        set = new HashSet<>();
    }

    public boolean insert(int val) {
        return set.add(val);
    }

    public boolean remove(int val) {
        return set.remove(val);
    }

    public int getRandom() {
        random = new Random();
        int i = random.nextInt(set.size());
        return (int) set.toArray()[i];
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(9);
        System.out.println(set.remove(8));
    }
}
