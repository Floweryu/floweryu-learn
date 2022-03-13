package utils;

import bean.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjunfeng
 * @date 2022/3/7 15:46
 */
public class CompareTest {
    @Test
    public void sortTest() {
        Integer[] nums = new Integer[]{1, 4, 3, 5, 2, 7, 6};
        Arrays.sort(nums, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (Integer i : nums) {
            System.out.print(i + "  ");
        }
    }
}
