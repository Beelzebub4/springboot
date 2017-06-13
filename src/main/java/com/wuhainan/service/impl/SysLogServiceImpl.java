package com.wuhainan.service.impl;

import com.wuhainan.dao.SysLogDao;
import com.wuhainan.entity.SysLog;
import com.wuhainan.service.SysLogService;
import com.wuhainan.utils.BtsParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志业务接口实现
 * <br>Created by Admin on 2017/5/5.
 * <br>星期五 at 16:43.
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogDao dao;

    @Override
    public void save(SysLog sysLog) {
        dao.insert(sysLog);
    }

    @Override
    public Long allCount(BtsParams params) {
        return dao.allCount(params);
    }

    @Override
    public List<SysLog> logPageList(BtsParams params) {
        return dao.logPageList(params);
    }
}
