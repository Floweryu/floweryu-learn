package com.floweryu.composite;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Floweryu
 * @date 2021/11/28 20:26
 */
@Data
public class HeadDepartment implements Department{
    private Integer id;
    private String name;

    private List<Department> childDepartments;

    public HeadDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.childDepartments = new ArrayList<>();
    }

    @Override
    public void printDepartmentName() {
        childDepartments.forEach(Department::printDepartmentName);
    }

    public void addDepartment(Department department) {
        childDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }
}
