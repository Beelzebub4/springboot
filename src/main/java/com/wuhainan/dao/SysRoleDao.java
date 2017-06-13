package com.wuhainan.dao;

import com.wuhainan.entity.SysRole;
import com.wuhainan.utils.BtsParams;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色数据访问接口
 * <br>Created by Admin on 2017/5/9.
 * <br>星期二 at 16:42.
 */
@Repository
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 根据创建者id查询的角色集合
     *
     * @param createUserId 创建者id
     * @return 角色集合
     */
    @SqlStatement(params = "createUserId")
    List<SysRole> roleList(long createUserId);

    /**
     * 根据创建者id查询的角色id集合
     *
     * @param createUserId 创建者id
     * @return 角色id集合
     */
    @SqlStatement(params = "createUserId")
    List<Long> roleIdList(long createUserId);

    /**
     * 角色分页列表
     *
     * @param params 分页参数
     * @return 分页列表
     */
    @SqlStatement(params = "params,createUserId")
    List<SysRole> rolePageList(BtsParams params, Long createUserId);

    /**
     * 根据角色id查找角色
     *
     * @param roleId 角色id
     * @return 角色
     */
    @SqlStatement(params = "roleId")
    SysRole findById(Long roleId);

    /**
     * 总记录数
     *
     * @param params 参数
     * @return 总数
     */
    @SqlStatement(params = "params")
    Long allCount(BtsParams params);
}
