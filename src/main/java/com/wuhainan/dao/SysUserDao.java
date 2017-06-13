package com.wuhainan.dao;

import com.wuhainan.entity.SysUser;
import com.wuhainan.utils.BtsParams;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* 用户数据访问接口
* gen by beetlsql mapper 2017-05-04
*/
@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @SqlStatement(params = "username")
    SysUser findByName(String username);

    /**
     * 根据用户Id查询用户信息
     *
     * @param userId 用户Id
     * @return 用户信息
     */
    @SqlStatement(params = "userId")
    SysUser findById(Long userId);

    /**
     * 查询用户分页列表
     *
     * @param params 分页参数
     * @return 用户分页列表
     */
    @SqlStatement(params = "params")
    List<SysUser> userPageList(BtsParams params);

    /**
     * 根据用户Id查询权限信息
     *
     * @param userId 用户id
     * @return 用户权限列表
     */
    @SqlStatement(params = "userId", returnType = String.class)
    List<String> userPermsList(Long userId);

    /**
     * 根据用户Id查询菜单Id列表
     *
     * @param userId 用户Id
     * @return 菜单id列表
     */
    @SqlStatement(params = "userId", returnType = Long.class)
    List<Long> userMenuIdList(Long userId);

    /**
     * ajax获取用户名
     *
     * @param usernameAjax 输入框实时用户名
     * @return 查询的用户名
     */
    @SqlStatement(params = "usernameAjax")
    String ajaxExistUser(String usernameAjax);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    @SqlStatement(params = "userId,oldPassword,newPassword",
            returnType = Integer.class)
    Integer updatePassword(Long userId, String oldPassword, String newPassword);

    /**
     * 总记录数
     *
     * @param params 参数
     * @return 总数
     */
    @SqlStatement(params = "params")
    Long allCount(BtsParams params);

}
