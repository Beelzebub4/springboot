package com.wuhainan.service;

import com.wuhainan.entity.Tables;
import com.wuhainan.utils.BtsParams;
import org.beetl.sql.core.SQLManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据库表业务接口
 * <br>Created by Admin on 2017/4/28.
 * <br>星期五 at 14:37.
 */
@Service
public interface TablesService {

    /**
     * 数据库表分页查询
     *
     * @param params 参数
     * @return 数据库表分页集合
     */
    List<Tables> tablePageList(BtsParams params);

    /**
     * 总数量
     *
     * @return Long型数值
     */
    Long allCount(BtsParams params);

    /**
     * sqlManager
     *
     * @return sqlManager
     */
    SQLManager getSQLManager();
}
