package code.daily;

/**
 * 2544. 交替数字和
 * https://leetcode.cn/problems/alternating-digit-sum/
 * 给你一个正整数 n 。n 中的每一位数字都会按下述规则分配一个符号：
 * <p>
 * 最高有效位 上的数字分配到 正 号。
 * 剩余每位上数字的符号都与其相邻数字相反。
 * 返回所有数字及其对应符号的和。
 * <p>
 * 输入：n = 521
 * 输出：4
 * 解释：(+5) + (-2) + (+1) = 4
 * @author: zhangjunfeng
 * @createTime: 2023/07/12
 */
public class Number2544 {
    public int alternateDigitSum(int n) {
        char[] charArray = String.valueOf(n).toCharArray();
        int sum = 0;
        boolean falg = true;
        for (char ch : charArray) {
            sum += falg ? (ch - '0') : ('0' - ch);
            falg = !falg;
        }
        return sum;
    }

    public static void main(String[] args) {
        Number2544 number2544 = new Number2544();
        int i = number2544.alternateDigitSum(521);
    }
}
