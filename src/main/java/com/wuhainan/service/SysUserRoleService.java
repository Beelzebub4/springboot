package com.wuhainan.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户-角色业务接口
 * <br>Created by Admin on 2017/5/11.
 * <br>星期四 at 14:24.
 */
@Service
public interface SysUserRoleService {

    /**
     * 新增或修改
     *
     * @param userId     用户id
     * @param roleIdList 角色id集合
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据角色id删除
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(Long roleId);

    /**
     * 根据用户id删除
     *
     * @param userId 用户id
     */
    void deleteByUserId(Long userId);

}
