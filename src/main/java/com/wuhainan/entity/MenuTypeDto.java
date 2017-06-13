package com.wuhainan.entity;

/**
 * 菜单类型对象
 * <br>Created by Admin on 2017/5/17.
 * <br>星期三 at 10:06.
 */
public class MenuTypeDto {
    private Integer value;//值
    private String description;//描述

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
