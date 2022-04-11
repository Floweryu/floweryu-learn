package utils;

import bean.Employee;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
    
    @Test
    public void timeTest() {
        long time = LocalDateTime.now().minus(90, ChronoUnit.DAYS).toEpochSecond(ZoneOffset.of("+8")) * 1000;
        Date date = new Date(time);
        System.out.println(time);
        System.out.println(date);
    }
    
    @Test
    public void setTest() {
        Set<String> oldContentDelete = new HashSet<String>() {
            {
                add("ssss_whitelist_zili_PUGC");
                // ssss_zili_PUGC_leveln 判断时使用前缀
                add("ssss_zili_PUGC_level");
            }
        };
        
        String str = "ssss_whitelist_zili_PUGC,ssss_1133,ssss_zili_PUGC_level,wedds";
        String[] tags = str.split(",");
        List<String> tagList = new ArrayList<>(Arrays.asList(tags));
        tagList.removeAll(oldContentDelete);
        int level = 1;
        tagList.add("ssss_zili_PUGC_level" + level);
        System.out.println(tagList);
    }
    
}
