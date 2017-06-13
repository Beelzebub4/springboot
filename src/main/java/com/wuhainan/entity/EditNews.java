package com.wuhainan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beetl.sql.core.TailBean;

import java.util.Date;

/**
 * <br>Created by 吴海南 on 2017/6/5.
 * <br>星期一 at 11:34.
 */
public class EditNews extends TailBean {
    private static final long serialVersionUID = 7671067165198734722L;
    private Long id;
    private String content;
    private String title;
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
