package com.wuhainan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beetl.sql.core.TailBean;

import java.io.Serializable;
import java.util.Date;

/*
* 系统日志实体类
* gen by beetlsql 2017-05-05
*/
public class SysLog extends TailBean implements Serializable {
    private static final long serialVersionUID = 989484287347562739L;
    private Long id;
    //IP地址
    private String ip;
    //请求方法
    private String method;
    //用户操作
    private String operation;
    //请求参数
    private String params;
    //用户名
    private String username;
    //创建时间
    private Date createDate;

    public SysLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
