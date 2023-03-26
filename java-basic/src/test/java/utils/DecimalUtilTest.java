package utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjunfeng
 * @date 2022/5/11 15:02
 */
public class DecimalUtilTest {

    public static double mulScale4(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.multiply(b2).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    @Test
    public void mainTest() {
        List<String> webLinkList = new ArrayList<>();
        webLinkList.add("111");
        webLinkList.add("222");
        webLinkList.add("  ");
        List<String> lowQualityLinkList = new ArrayList<>();
        lowQualityLinkList.add("   ");
        lowQualityLinkList.add("333");
        webLinkList.addAll(lowQualityLinkList);
        // 过滤掉空串的link
        List<String> notBlackList = webLinkList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        System.out.println(notBlackList);
    }
}
