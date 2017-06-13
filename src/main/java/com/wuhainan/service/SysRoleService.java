package com.wuhainan.service;

import com.wuhainan.entity.SysRole;
import com.wuhainan.utils.BtsParams;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务接口
 * <br>Created by Admin on 2017/5/9.
 * <br>星期二 at 16:43.
 */
@Service
public interface SysRoleService {

    /**
     * 角色分页列表
     *
     * @param params 分页参数
     * @return 分页列表
     */
    List<SysRole> rolePageList(BtsParams params, Long createUserId);

    /**
     * 总数量
     *
     * @return total
     */
    Long allCount(BtsParams params);

    /**
     * 根据创建者id查询的角色集合
     *
     * @param createUserId 创建者id
     * @return 角色集合
     */
    List<SysRole> roleList(long createUserId);

    /**
     * 根据创建者id查询的角色id集合
     *
     * @param createUserId 创建者id
     * @return 角色id集合
     */
    List<Long> roleIdList(long createUserId);

    /**
     * 根据角色id查找角色
     *
     * @param roleId 角色id
     * @return 角色
     */
    SysRole findById(Long roleId);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    void deleteById(Long roleId);

    /**
     * 增加角色
     *
     * @param sysRole 角色
     */
    void addRole(SysRole sysRole);

    /**
     * 编辑角色
     *
     * @param sysRole 角色
     */
    void editRole(SysRole sysRole);
}
