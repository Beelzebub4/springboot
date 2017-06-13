package com.wuhainan.service;

import com.wuhainan.entity.SysMenu;
import com.wuhainan.entity.SysUser;
import com.wuhainan.utils.BtsParams;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务接口
 * <br>Created by Admin on 2017/5/4.
 * <br>星期四 at 9:20.
 */
@Service
public interface SysUserService {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByName(String username);

    /**
     * 根据用户Id查询用户信息
     *
     * @param userId 用户Id
     * @return 用户信息
     */
    SysUser findById(Long userId);

    /**
     * 查询用户分页列表
     *
     * @param params 分页参数
     * @return 用户分页列表
     */
    List<SysUser> userPageList(BtsParams params);

    /**
     * 根据用户Id查询权限信息
     *
     * @param userId 用户id
     * @return 用户权限列表
     */
    List<String> userPermsList(Long userId);

    /**
     * 根据用户Id查询菜单Id列表
     *
     * @param userId 用户Id
     * @return 菜单id列表
     */
    List<SysMenu> userMenuIdList(Long userId);

    /**
     * ajax获取用户名
     *
     * @param usernameAjax 输入框实时用户名
     * @return 查询的用户名
     */
    String ajaxExistUser(String usernameAjax);

    /**
     * 根据Id删除用户
     *
     * @param userId 用户id
     */
    void deleteById(Long userId);

    /**
     * 查询总记录数 用于分页参数total
     *
     * @return total
     */
    Long allCount(BtsParams params);

    /**
     * 增加用户
     *
     * @param sysUser 用户
     */
    void addUser(SysUser sysUser);

    /**
     * 编辑用户
     *
     * @param sysUser 用户
     */
    void editUser(SysUser sysUser);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    Integer updatePassword(Long userId, String oldPassword, String newPassword);
}
