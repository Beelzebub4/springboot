package com.wuhainan.entity;

import org.beetl.sql.core.TailBean;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>Created by 吴海南 on 2017/6/2.
 * <br>星期五 at 11:10.
 */
public class SysFileUpload extends TailBean implements Serializable {
    private static final long serialVersionUID = 4603200095803707573L;
    private Long id;
    private String fileName;
    private String fileUrl;
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
