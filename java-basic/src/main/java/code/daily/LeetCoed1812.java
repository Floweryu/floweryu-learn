package code.daily;

/**
 * @author Floweryu
 * @date 2022/12/8 19:18
 */
public class LeetCoed1812 {
    public boolean squareIsWhite(String coordinates) {
        int first = coordinates.charAt(0) - 96;
        int second = coordinates.charAt(1) - '0';
        return (first + second) % 2 != 0;
    }
    
    public static void main(String[] args) {
        System.out.println('b' - 'a' + 1);
    }
}
