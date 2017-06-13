package com.wuhainan.service;

import com.wuhainan.entity.SysMenu;
import com.wuhainan.utils.BtsParams;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统菜单业务接口
 * <br>Created by Admin on 2017/5/8.
 * <br>星期一 at 20:54.
 */
@Service
public interface SysMenuService {

    /**
     * 根据父菜单查询子菜单
     *
     * @param parentId 父id
     * @param menuId   菜单id
     * @return 子菜单集合
     */
    List<SysMenu> queryMenuParentId(Long parentId, List<Long> menuId);

    /**
     * 分页查询菜单列表
     *
     * @param params 参数
     * @return 菜单分页集合
     */
    List<SysMenu> menuPageList(BtsParams params);

    /**
     * 总数量
     *
     * @return Long型数值
     */
    Long allCount(BtsParams params);

    /**
     * 角色拥有的菜单集合
     *
     * @param userId 用户id
     * @return 菜单集合
     */
    List<SysMenu> roleMenuList(Long userId);

    /**
     * 保存菜单
     *
     * @param sysMenu 菜单
     */
    void addMenu(SysMenu sysMenu);

    /**
     * 编辑菜单
     *
     * @param sysMenu 菜单
     */
    void editMenu(SysMenu sysMenu);

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     */
    void deleteById(Long menuId);

    /**
     * 不含按钮类型的菜单列表
     *
     * @return 不含按钮
     */
    List<SysMenu> noButtonMenuList();

    /**
     * 菜单id集合查询全部菜单集合
     *
     * @param menuIdList 菜单id集合
     * @return 菜单集合
     */
    List<SysMenu> getAllMenuList(List<Long> menuIdList);
}
