package com.wuhainan.controller;

import com.wuhainan.entity.SysLog;
import com.wuhainan.service.SysLogService;
import com.wuhainan.utils.BtsParams;
import com.wuhainan.utils.PageUtils;
import com.wuhainan.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志控制层
 * <br>Created by Admin on 2017/5/6.
 * <br>星期六 at 18:15.
 */
@Controller
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    /**
     * 主页面
     *
     * @return 返回syslog页面
     */
    @RequestMapping("sysLog")
    public R sysLog() {
        return R.ok("sysLog");
    }

    /**
     * 日志分页列表
     *
     * @param params    分页参数
     * @param pageUtils 分页封装
     * @return 日志分页集合
     */
    @ResponseBody
    @RequiresPermissions("sys:log:list")
    @RequestMapping("sysLogPageList")
    public PageUtils sysLogPageList(BtsParams params, PageUtils pageUtils) {
        List<SysLog> logList = sysLogService.logPageList(params);
        Long total = sysLogService.allCount(params);//总记录数
        pageUtils.setTotal(total);
        pageUtils.setRows(logList);
        return pageUtils;
    }
}
