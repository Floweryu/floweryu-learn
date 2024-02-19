package code.hot;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 方法一：排序
 *
 */
public class Hot49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        // key: 单词按序排列后的字符串  value: 该单词的变种list
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 排序，以便下面拍段
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sb = Arrays.toString(charArray);

            // 对应单词放入对应的集合中
            List<String> strings = map.getOrDefault(sb, new ArrayList<>());
            strings.add(str);
            map.put(sb, strings);
        }
        return new ArrayList<>(map.values());
    }
}
