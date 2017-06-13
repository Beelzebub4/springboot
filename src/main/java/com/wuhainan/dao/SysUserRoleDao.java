package com.wuhainan.dao;

import com.wuhainan.entity.SysUserRole;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.annotatoin.SqlStatementType;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br>Created by Admin on 2017/5/11.
 * <br>星期四 at 8:50.
 */
@Repository
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

    /**
     * 新增或修改
     *
     * @param userId     用户id
     * @param roleIdList 角色id集合
     */
    @SqlStatement(params = "userId,roleIdList", type = SqlStatementType.INSERT)
    void saveUserRole(Long userId, List<Long> roleIdList);

    /**
     * 根据角色id删除
     *
     * @param roleId 角色id
     */
    @SqlStatement(params = "roleId")
    void deleteByRoleId(Long roleId);

    /**
     * 根据用户id删除
     *
     * @param userId 用户id
     */
    @SqlStatement(params = "userId")
    void deleteByUserId(Long userId);

}
