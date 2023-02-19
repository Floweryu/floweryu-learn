package code.dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Floweryu
 * @date 2022/12/6 21:47
 */
public class LeetCode1805 {
    public static int numDifferentIntegers(String word) {
        String[] split = word.split("[a-z]");
        System.out.println(Arrays.toString(split));
        Set<String> res = new HashSet<>();
        for (String str : split) {
            if (str.length() != 0) {
                res.add(str.replaceFirst("^0*", ""));
            }
        }
        return res.size();
    }
    
    public static void main(String[] args) {
        int a = numDifferentIntegers("a1b01c001");
        System.out.println(a);
    }
}
