package com.wuhainan.utils;

import java.io.Serializable;

/**
 * bootstrap表格参数封装
 * <br>Created by Admin on 2017/5/10.
 * <br>星期三 at 15:21.
 */
public class BtsParams implements Serializable {
    private static final long serialVersionUID = 2452002359572798632L;
    private Integer offset;//起始数
    private Integer limit;//页面大小
    private String search;//搜索内容
    private String sort;//排序列名称
    private String order;//排序方法 ASC DESC
    private String startTime;//开始时间
    private String endTime;//结束时间
    private Integer selectBox;//下拉框

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getSelectBox() {
        return selectBox;
    }

    public void setSelectBox(Integer selectBox) {
        this.selectBox = selectBox;
    }
}
