package code.hot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Floweryu
 * @date 2024/3/8 14:55:01
 */
public class Hot17 {
    private List<String> ans = new ArrayList<>();
    HashMap<Character, String> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return ans;
        }
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        dfs(digits, 0, new StringBuilder());
        return ans;
    }

    private void dfs(String digits, int index, StringBuilder sb) {
        if (sb.length() == digits.length()) {
            ans.add(new String(sb));
            return;
        }
        String dict = map.get(digits.charAt(index));
        for (int i = 0; i < dict.length(); i++) {
            sb.append(dict.charAt(i));
            dfs(digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
