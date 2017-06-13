package com.wuhainan.entity;

import org.beetl.sql.core.TailBean;

import java.io.Serializable;

/*
* 角色-菜单实体类
* gen by beetlsql 2017-05-06
*/
public class SysRoleMenu extends TailBean implements Serializable {
    private static final long serialVersionUID = 8274306025139788347L;
    private Long id;
    //菜单ID
    private Long menuId;
    //角色ID
    private Long roleId;

    public SysRoleMenu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


}
