package code.daily;

/**
 * @author Floweryu
 * @date 2022/12/15 19:42
 */
public class LeetCode1945 {
    public static int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
             sb.append(s.charAt(i) - 'a' + 1);
        }
    
        int sum = 0;
        for (int i= 0; i < k; i++) {
            sum = 0;
            String s1 = sb.toString();
            for (int j = 0; j < s1.length(); j++) {
                sum += s1.charAt(j) - '0';
            }
            sb = new StringBuilder(String.valueOf(sum));
        }
        return sum;
    }
    
    public static void main(String[] args) {
        int leetcode = getLucky("iiii", 1);
        System.out.println(leetcode);
    }
}
