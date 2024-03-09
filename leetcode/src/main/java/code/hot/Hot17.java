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

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        dfs(digits, 0, new ArrayList<>());
    }

    private void dfs(String digits, int index, List<String> arr) {
        for (int i = index; i < digits.length(); i++) {
            String dict = map.get(digits.charAt(i));

        }
    }
}
