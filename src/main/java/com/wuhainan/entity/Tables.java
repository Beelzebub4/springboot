package com.wuhainan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beetl.sql.core.TailBean;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表格实体类
 * <br>Created by Admin on 2017/4/28.
 * <br>星期五 at 10:28.
 */
public class Tables extends TailBean implements Serializable {

    private static final long serialVersionUID = 7275080897655353815L;
    private String tableName;//表明
    private Date createTime;//创建时间
    private String tableComment;//表注释
    private Integer tableRows;//表数据量
    private String engine;//引擎

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Integer getTableRows() {
        return tableRows;
    }

    public void setTableRows(Integer tableRows) {
        this.tableRows = tableRows;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
