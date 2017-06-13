package com.wuhainan.dao;

import com.wuhainan.entity.Tables;
import com.wuhainan.utils.BtsParams;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br>Created by Admin on 2017/4/28.
 * <br>星期五 at 14:28.
 */
@Repository
public interface TablesDao extends BaseMapper<Tables> {

    @SqlStatement(params = "params")
    List<Tables> tablePageList(BtsParams params);

    /**
     * 总记录数
     *
     * @param params 参数
     * @return 总数
     */
    @SqlStatement(params = "params")
    Long allCount(BtsParams params);
}
