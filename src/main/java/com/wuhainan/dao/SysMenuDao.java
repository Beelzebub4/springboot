package com.wuhainan.dao;

import com.wuhainan.entity.SysMenu;
import com.wuhainan.utils.BtsParams;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br>Created by Admin on 2017/5/8.
 * <br>星期一 at 20:53.
 */
@Repository
public interface SysMenuDao extends BaseMapper<SysMenu> {

    /**
     * 根据父菜单查询子菜单
     *
     * @param parentId 父id
     * @return 子菜单集合
     */
    @SqlStatement(params = "parentId")
    List<SysMenu> queryMenuParentId(Long parentId);

    /**
     * 分页查询菜单列表
     *
     * @param params 参数
     * @return 菜单分页集合
     */
    @SqlStatement(params = "params")
    List<SysMenu> menuPageList(BtsParams params);

    /**
     * 角色拥有的菜单集合
     *
     * @param userId 用户id
     * @return 菜单集合
     */
    @SqlStatement(params = "userId")
    List<SysMenu> roleMenuList(Long userId);

    /**
     * 不含按钮类型的菜单列表
     *
     * @return 不含按钮
     */
    List<SysMenu> noButtonMenuList();

    /**
     * 总记录数
     *
     * @param params 参数
     * @return 总数
     */
    @SqlStatement(params = "params")
    Long allCount(BtsParams params);
}
