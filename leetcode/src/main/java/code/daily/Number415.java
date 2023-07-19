package code.daily;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/07/17
 */
public class Number415 {
    public String addStrings(String num1, String num2) {
        StringBuilder reverse1 = new StringBuilder(num1).reverse();
        StringBuilder reverse2 = new StringBuilder(num2).reverse();

        int add = 0;
        int i = 0, j = 0;
        StringBuilder ans = new StringBuilder();
        while (i < num1.length() || j < num2.length() || add != 0) {
            int v1 = i < num1.length() ? reverse1.charAt(i) - '0' : 0;
            int v2 = j < num2.length() ? reverse2.charAt(i) - '0' : 0;
            int tmp = v1 + v2 + add;
            int rest = tmp % 10;
            ans.append(rest);
            add = tmp / 10;
            i++;
            j++;
        }
        return ans.reverse().toString();
    }
}
