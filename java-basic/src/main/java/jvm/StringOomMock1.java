package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Floweryu
 * @date 2023/10/21 23:45
 */
public class StringOomMock1 {
    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
