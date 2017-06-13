package com.wuhainan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beetl.sql.core.TailBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
* 角色实体类
* gen by beetlsql 2017-05-06
*/
public class SysRole extends TailBean implements Serializable {
    private static final long serialVersionUID = -7645368641919713992L;
    private Long roleId;
    //创建者ID
    private Long createUserId;
    //备注
    private String remark;
    //角色名称
    private String roleName;
    //创建时间
    private Date createTime;
    //角色拥有的菜单
    private List<Long> menuIdList;

    public SysRole() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
