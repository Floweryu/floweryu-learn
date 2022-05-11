package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zhangjunfeng
 * @date 2022/5/10 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String name;
    private int qty;
    private BigDecimal price;
}
