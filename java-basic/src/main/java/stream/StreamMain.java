package stream;

import bean.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangjunfeng
 * @date 2022/5/10 17:07
 */
public class StreamMain {
    public static void main(String[] args) {

        //3 apple, 2 banana, others 1
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        Map<String, List<Item>> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName));

        System.out.println(counting);
        
        
        List<Item> arrays = new ArrayList<>();
        Item item = new Item();
        arrays.add(item);
        item.setName("aaa");
        item.setQty(1);
        System.out.println(arrays);

    }
}
