package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/06/27
 */
public class DiffUtil {

    public static Set<String> readContent(String path) throws Exception {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String[] split = builder.toString().split(",");
        Set<String> set = Arrays.stream(split).collect(Collectors.toSet());
        return set;
    }

    public static Set<String> diffContent(Set<String> set1, Set<String> set2) {
        Set<String> res = new HashSet<>();
        for (String item : set2) {
            if (set1.contains(item)) {
                res.add(item);
            }
        }
        return res;
    }
}
