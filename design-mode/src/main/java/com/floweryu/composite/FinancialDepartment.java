package com.floweryu.composite;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/11/28 20:24
 */
@Data
public class FinancialDepartment implements Department{
    private Integer id;
    private String name;
    
    public FinancialDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void printDepartmentName() {
        System.out.println(getClass().getSimpleName());
    }
}
