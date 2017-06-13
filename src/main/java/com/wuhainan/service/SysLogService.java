package com.wuhainan.service;

import com.wuhainan.entity.SysLog;
import com.wuhainan.utils.BtsParams;

import java.util.List;

/**
 * 操作日志业务接口
 * <br>Created by Admin on 2017/5/5.
 * <br>星期五 at 16:42.
 */
public interface SysLogService {

    /**
     * 保存操作日志
     *
     * @param sysLog 日志
     */
    void save(SysLog sysLog);

    /**
     * 总数量
     *
     * @return Long型数值
     */
    Long allCount(BtsParams params);

    /**
     * 操作日志分页查询列表
     *
     * @param params 参数
     * @return 操作日志分页集合
     */
    List<SysLog> logPageList(BtsParams params);
}
