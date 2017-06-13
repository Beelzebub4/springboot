package com.wuhainan.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色-菜单业务接口
 * <br>Created by Admin on 2017/5/11.
 * <br>星期四 at 14:23.
 */
@Service
public interface SysRoleMenuService {

    /**
     * 新增或修改
     *
     * @param roleId     角色id
     * @param menuIdList 菜单id集合
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 增加 保证管理员最大权限
     *
     * @param roleId 角色id
     * @param menuId 菜单id
     */
    void addRoleMenu(Long roleId, Long menuId);

    /**
     * 删除
     *
     * @param menuId 菜单id
     */
    void deleteByMenuId(Long menuId);

    /**
     * 删除
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(Long roleId);
}
