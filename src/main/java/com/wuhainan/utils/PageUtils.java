package com.wuhainan.utils;

import java.io.Serializable;
import java.util.List;

/**
 * <br>Created by Admin on 2017/5/9.
 * <br>星期二 at 21:10.
 */
public class PageUtils implements Serializable {
    private Long total;//总数
    private List rows;//列表数据

    public PageUtils() {
    }

    public PageUtils(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

}
