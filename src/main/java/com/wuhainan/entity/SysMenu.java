package com.wuhainan.entity;

import org.beetl.sql.core.TailBean;

import java.io.Serializable;
import java.util.List;

/*
* 菜单实体类
* gen by beetlsql 2017-05-07
*/
public class SysMenu extends TailBean implements Serializable {
    private static final long serialVersionUID = -728586595293387569L;
    private Long menuId;
    //排序
    private Integer orderNum;
    //类型   0：目录   1：菜单   2：按钮
    private Integer type;
    //菜单图标
    private String icon;
    //菜单名称
    private String name;
    //父菜单ID，一级菜单为0
    private Long parentId;
    //授权(多个用逗号分隔，如：user:list,user:create)
    private String perms;
    //菜单URL
    private String url;
    //父菜单名称
    private String parentName;
    //ztree属性
    private Boolean open;

    private List<?> list;

    public SysMenu() {
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
