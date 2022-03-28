package code.daily;

/**
 * @author zhangjunfeng
 * @date 2022/3/25 17:40
 */
public class LeetCode172 {

    public int trailingZeroes(int n) {
        int count = 0;
        while (n != 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(9/5);
    }
}
