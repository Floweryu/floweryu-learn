package code.daily;

/**
 * @author zhangjunfeng
 * @date 2022/12/27 19:29
 */
public class LeetCode2027 {
    public static int minimumMoves(String s) {
        int i = 0;
        int res = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'X') {
                i += 3;
                res++;
            } else {
                i++;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        
    }
}
