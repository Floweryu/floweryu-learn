package code.common;

import org.junit.Test;

import java.util.Set;

/**
 * @author zhangjunfeng
 * @date 2023/2/6 19:41
 */
public class LRUCacheTest {
    
    @Test
    public void test() {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put(1, 11);
        lruCache.put(2, 22);
        lruCache.put(3, 33);
        lruCache.put(4, 44);
        lruCache.put(5, 55);
        lruCache.print();
        int i = lruCache.get(1);
        lruCache.print();
        lruCache.put(6, 66);
        lruCache.print();
    }

    @Test
    public void test11() {
        Set<String> nacos = Set.of("22061218C", "2304FPN6DC", "2109119BC", "Mi Note 2", "MI CC9 Pro", "M2012K11C", "Redmi K20 Pro Premiu", "MI 8", "Redmi K20 Pro", "MI 6", "M2007J3SG", "MI 5s Plus", "Mi Note 3", "M2004J7BC", "M2012K11AC", "M2012K10C", "MI CC 9 Meitu Edition", "M2101K9C", "2107119DC", "2112123AC", "2201123C", "21051182C", "M2102K1C", "M2007J1SC", "M2011J18C", "2106118C", "Mi 10 Pro", "M2102K1AC", "M2011K2C", "MI 8 Explorer Editio", "Mi9 Pro 5G", "MIX", "MI CC9 Pro Premium E", "Mi 10", "MIX 2", "Mi MIX 2", "MIX 2S", "MIX 3", "MI 9 Transparent Edi", "M2102J2SC", "Redmi K30 Pro", "MI 8 UD", "MI 9", "Redmi K30 Pro Zoom E", "2201122C", "Xiaomi 12X", "Xiaomi 12", "Xiaomi 12 Pro", "M2105K81AC", "M2105K81C", "21121210C", "22011211C", "22041216C", "22041216UC", "2203121C", "2206122SC", "2206123SC", "22081212C", "2210132C", "M2006J10C", "Redmi K20", "Redmi K30 5G", "Mi 9 SE", "Redmi K30 5G Speed", "Redmi K30i 5G", "MI 4LTE", "MI 5", "21091116C", "M2002J9E", "2209129SC", "2211133C", "2211133G");
        Set<String> cms = Set.of(  "2109119BC", "Mi Note 2", "MI CC9 Pro", "M2012K11C", "Redmi K20 Pro Premiu", "MI 8", "Redmi K20 Pro", "MI 6", "M2007J3SG", "MI 5s Plus", "Mi Note 3", "M2004J7BC", "M2012K11AC", "M2012K10C", "MI CC 9 Meitu Editio", "M2101K9C", "2107119DC", "2112123AC", "2201123C", "21051182C", "M2102K1C", "M2007J1SC", "M2011J18C", "2106118C", "Mi 10 Pro", "M2102K1AC", "M2011K2C", "MI 8 Explorer Editio", "Mi9 Pro 5G", "MIX", "MI CC9 Pro Premium E", "Mi 10", "MIX 2", "Mi MIX 2", "MIX 2S", "MIX 3", "MI 9 Transparent Edi", "M2102J2SC", "Redmi K30 Pro", "MI 8 UD", "MI 9", "Redmi K30 Pro Zoom E", "2201122C ", " Xiaomi 12X", "Xiaomi 12", "Xiaomi 12 Pro", "M2105K81AC", "M2105K81C ", "21121210C", "22011211C", "22041216C", "22041216UC", "2203121C", "2206122SC", "2206123SC", "2207122MC", "22081212C", "2210132C", "23043RP34C", "22061218C");
        int count = 0;
        for (String item : nacos) {
            if (!cms.contains(item)) {
                count++;
                System.out.println(item);
            }
        }
        System.out.println(count);
    }
}
