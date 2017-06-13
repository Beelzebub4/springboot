package com.wuhainan.service.impl;

import com.wuhainan.dao.SysMenuDao;
import com.wuhainan.entity.SysMenu;
import com.wuhainan.service.SysMenuService;
import com.wuhainan.service.SysRoleMenuService;
import com.wuhainan.utils.BtsParams;
import com.wuhainan.utils.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单业务接口实现
 * <br>Created by Admin on 2017/5/8.
 * <br>星期一 at 20:54.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> queryMenuParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = sysMenuDao.queryMenuParentId(parentId);
        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> menuPageList(BtsParams params) {
        return sysMenuDao.menuPageList(params);
    }

    @Override
    public Long allCount(BtsParams params) {
        return sysMenuDao.allCount(params);
    }

    @Override
    public List<SysMenu> roleMenuList(Long userId) {
        return sysMenuDao.roleMenuList(userId);
    }

    @Override
    public void addMenu(SysMenu sysMenu) {
        sysMenuDao.insert(sysMenu, true);
        //新增菜单加入到管理员角色中 保证管理员账号拥有最大权限
        sysRoleMenuService.addRoleMenu(Constant.SUPER_ROLE, sysMenu.getMenuId());
    }

    @Override
    public void editMenu(SysMenu sysMenu) {
        sysMenuDao.updateById(sysMenu);
    }

    @Override
    public void deleteById(Long menuId) {
        sysMenuDao.deleteById(menuId);
        sysRoleMenuService.deleteByMenuId(menuId);
    }

    @Override
    public List<SysMenu> noButtonMenuList() {
        return sysMenuDao.noButtonMenuList();
    }

    @Override
    public List<SysMenu> getAllMenuList(List<Long> menuIdList) {
        //根菜单列表
        List<SysMenu> menuList = queryMenuParentId(0L, menuIdList);
        //递归查询子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getType() == Constant.MenuType.CATALOG.getValue()) {//目录
                menu.setList(getMenuTreeList(
                        queryMenuParentId(menu.getMenuId(), menuIdList),
                        menuIdList));
            }
            subMenuList.add(menu);
        }
        return subMenuList;
    }
}
