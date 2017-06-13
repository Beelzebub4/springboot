package com.wuhainan.controller;

import com.wuhainan.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Sql监控页面
 * <br>Created by Admin on 2017/5/22.
 * <br>星期一 at 13:27.
 */
@Controller
public class DruidSqlController extends AbstractController{
    @RequestMapping("druidLogin")
    public R druidIndex() {
        logger.info(getSysUser().getUsername()+"登录druid监控");
        return R.ok("druidLogin");
    }
}
