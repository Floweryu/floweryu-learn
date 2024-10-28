package code.hot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://aleetcode.cn/problems/zigzag-conversion/description/?envType=study-plan-v2&envId=top-interview-150
 * z字形变换
 * @author Floweryu
 * @date 2024/6/24 22:07
 */
public class Interview6 {

    public String convert(String s, int numRows) {
        // P A Y P A L I S H I R I N G  4
        // 0 1 2 3 2 1 0 1 2 3 2 1 0 1
        if (numRows < 2) {
            return s;
        }
        HashMap<Integer, List<Character>> map = new HashMap<>();
        List<StringBuilder> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            res.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char ch : s.toCharArray()) {
            res.get(i).append(ch);
            // 这时需要变换方向
            if (i == numRows - 1 || i == 0) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder row : res) {
            sb.append(row);
        }
        return sb.toString();
    }
}
