package com.wuhainan.dao;

import com.wuhainan.entity.SysRoleMenu;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.annotatoin.SqlStatementType;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色-菜单数据访问接口
 * <br>Created by Admin on 2017/5/11.
 * <br>星期四 at 8:50.
 */
@Repository
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    /**
     * 保存
     *
     * @param roleId     角色id
     * @param menuIdList 菜单id集合
     */
    @SqlStatement(params = "roleId,menuIdList", type = SqlStatementType.INSERT)
    void saveRoleMenu(Long roleId, List<Long> menuIdList);

    /**
     * 增加 保证管理员最大权限
     *
     * @param roleId 角色id
     * @param menuId 菜单id
     */
    @SqlStatement(params = "roleId,menuId")
    void addRoleMenu(Long roleId, Long menuId);

    /**
     * 删除
     *
     * @param menuId 菜单id
     */
    @SqlStatement(params = "menuId")
    void deleteByMenuId(Long menuId);

    /**
     * 删除
     *
     * @param roleId 菜单id
     */
    @SqlStatement(params = "roleId")
    void deleteByRoleId(Long roleId);
}
