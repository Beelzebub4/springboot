package com.wuhainan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 代码生成配置类
 * <br>Created by Admin on 2017/5/3.
 * <br>星期三 at 16:16.
 */
@Component
@ConfigurationProperties(prefix = "genCode")
public class GenConf {
    private String srcPath;//项目系统路径
    private String entityPkg;//实体类所在包
    private String daoPkg;//dao所在包
    private String sqlPath;//sql文件位置 默认即可

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getEntityPkg() {
        return entityPkg;
    }

    public void setEntityPkg(String entityPkg) {
        this.entityPkg = entityPkg;
    }

    public String getDaoPkg() {
        return daoPkg;
    }

    public void setDaoPkg(String daoPkg) {
        this.daoPkg = daoPkg;
    }

    public String getSqlPath() {
        return sqlPath;
    }

    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }
}
