package com.wuhainan.service.impl;

import com.wuhainan.dao.SysUserRoleDao;
import com.wuhainan.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <br>Created by Admin on 2017/5/11.
 * <br>星期四 at 14:25.
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleDao dao;

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if (userId != null && roleIdList != null && roleIdList.size() != 0) {
            System.out.println("userIdHaHa" + userId);
            dao.deleteByUserId(userId);//先删除原来的
            dao.saveUserRole(userId, roleIdList);
        }

    }

    @Override
    public void deleteByRoleId(Long roleId) {
        dao.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        dao.deleteByUserId(userId);
    }
}
