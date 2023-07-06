package utils;

import org.junit.Test;

import java.util.Set;

/**
 * @author: zhangjunfeng
 * @createTime: 2023/06/27
 */
public class DiffUtilTest {
    @Test
    public void diffContentTest() throws Exception {
        Set<String> set20 = DiffUtil.readContent("src/main/resources/content_0620.txt");
        Set<String> set21 = DiffUtil.readContent("src/main/resources/content_0621.txt");
        Set<String> set22 = DiffUtil.readContent("src/main/resources/content_0622.txt");
        Set<String> set23 = DiffUtil.readContent("src/main/resources/content_0623.txt");
        Set<String> set24 = DiffUtil.readContent("src/main/resources/content_0624.txt");
        Set<String> set25 = DiffUtil.readContent("src/main/resources/content_0625.txt");
        Set<String> set26 = DiffUtil.readContent("src/main/resources/content_0626.txt");
        Set<String> set27 = DiffUtil.readContent("src/main/resources/content_0627.txt");
        Set<String> res1 = DiffUtil.diffContent(set20, set21);
        Set<String> res2 = DiffUtil.diffContent(set20, set22);
        Set<String> res3 = DiffUtil.diffContent(set20, set23);
        Set<String> res4 = DiffUtil.diffContent(set23, set24);
        Set<String> res5 = DiffUtil.diffContent(set24, set25);
        Set<String> res6 = DiffUtil.diffContent(set24, set26);
        Set<String> res7 = DiffUtil.diffContent(set24, set27);
        System.out.println("比较结果set21中元素在set20中个数: " + res1.size());
        System.out.println("比较结果set22中元素在set21中个数: " + res2.size());
        System.out.println("比较结果set23中元素在set22中个数: " + res3.size());
        System.out.println("比较结果set24中元素在set23中个数: " + res4.size());
        System.out.println("比较结果set25中元素在set24中个数: " + res5.size());
        System.out.println("比较结果set26中元素在set25中个数: " + res6.size());
        System.out.println("比较结果set27中元素在set26中个数: " + res7.size());
//        System.out.println("比较结果set21中元素在set20中详情: " + res);

    }

}
