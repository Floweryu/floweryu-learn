package com.example.validation.controller.param;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author zhangjunfeng
 * @date 2021/11/18 19:50
 */
@Data
public class PersonParam {
    
    @NotNull(message = "name 不为 null")
    @NotBlank(message = "name 不为空串")
    private String name;
    
    @NotNull(message = "sex 不为 null")
    @DecimalMax(value = "100", message = "size <= 100")
    @Digits(integer = 10, fraction = 2)
    private Integer sex;
}
