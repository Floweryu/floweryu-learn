package code.dynamic;

import java.util.*;

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
    
    public String customSortString(String order, String s) {
        Map<Character, Integer> map = new HashMap<>(order.length());
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        
        Character[] characters = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            characters[i] = s.charAt(i);
        }
        
        Arrays.sort(characters, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.getOrDefault(o1, 27) - map.getOrDefault(o2, 27);
            }
        });
    
        StringBuilder sb = new StringBuilder();
        for (Character ch : characters) {
            sb.append(ch);
        }
        
        return sb.toString();
    }
}
