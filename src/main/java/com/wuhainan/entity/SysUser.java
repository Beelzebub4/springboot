package com.wuhainan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.AutoID;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * 用户实体类
 * gen by beetlsql 2017-05-04
 */
public class SysUser extends TailBean implements Serializable {
    private static final long serialVersionUID = 8345306249944001090L;
    private Long userId;//用户Id
    private Integer status;//是否可用状态
    private Long createUserId;//创建者Id
    private String email;//邮箱
    private String mobile;//手机
    private String password;//密码
    private String username;//用户名
    private Date createTime;//创建时间
    private List<Long> roleIdList;//角色Id列表

    public SysUser() {
    }

    @AutoID
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

}
