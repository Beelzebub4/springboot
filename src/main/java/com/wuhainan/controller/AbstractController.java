package com.wuhainan.controller;

import com.wuhainan.entity.SysUser;
import com.wuhainan.utils.ShiroUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共控制层
 * <br>Created by Admin on 2017/5/11.
 * <br>星期四 at 9:59.
 */
public abstract class AbstractController {

    Logger logger = LoggerFactory.getLogger(getClass());

    SysUser getSysUser() {
        return ShiroUtils.getSysUser();
    }

    protected Long getUserId() {
        return ShiroUtils.getUserId();
    }

    Subject getSubject() {
        return ShiroUtils.getSubject();
    }

    void userLogin(String username, String password) {
        ShiroUtils.login(username, password);
    }
}
