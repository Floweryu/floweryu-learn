package code.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjunfeng
 * @date 2022/3/24 17:01
 */
public class LeetCode118 {
    /**
     * 118.杨辉三角
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int a = 0; a < i + 1; a++) {
                if (a > 0 && a < i) {
                    tmp.add(res.get(i - 1).get(a - 1) + res.get(i - 1).get(a));
                } else {
                    tmp.add(1);
                }
            }
            res.add(tmp);
        }
        return res;
    }
}
