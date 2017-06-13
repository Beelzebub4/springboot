package com.wuhainan.service.impl;

import com.wuhainan.dao.SysRoleMenuDao;
import com.wuhainan.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <br>Created by Admin on 2017/5/11.
 * <br>星期四 at 14:24.
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        sysRoleMenuDao.deleteByRoleId(roleId);
        if (roleId != null && menuIdList != null && menuIdList.size() != 0) {
            sysRoleMenuDao.saveRoleMenu(roleId, menuIdList);
        }
    }

    @Override
    public void addRoleMenu(Long roleId, Long menuId) {
        sysRoleMenuDao.addRoleMenu(roleId, menuId);
    }

    @Override
    public void deleteByMenuId(Long menuId) {
        sysRoleMenuDao.deleteByMenuId(menuId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        sysRoleMenuDao.deleteByRoleId(roleId);
    }
}
