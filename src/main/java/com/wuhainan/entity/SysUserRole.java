package com.wuhainan.entity;

import org.beetl.sql.core.TailBean;

import java.io.Serializable;

/*
* 用户-角色实体类
* gen by beetlsql 2017-05-07
*/
public class SysUserRole extends TailBean implements Serializable {
    private static final long serialVersionUID = 8086447279799551651L;
    private Long id;
    //角色ID
    private Long roleId;
    //用户ID
    private Long userId;

    public SysUserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
