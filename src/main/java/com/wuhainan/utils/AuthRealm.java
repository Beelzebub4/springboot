package com.wuhainan.utils;

import com.wuhainan.entity.SysUser;
import com.wuhainan.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义权限登录验证
 * <br>Created by Admin on 2017/5/4.
 * <br>星期四 at 22:12.
 */
public class AuthRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;

    /**
     * 认证(登录时调用)
     *
     * @param token 用户输入的token
     * @return 登录信息
     * @throws AuthenticationException 异常信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;//用户输入的token
        String username = userToken.getUsername();
        String password = new String((char[]) userToken.getCredentials());
        //查询用户信息
        SysUser sysUser = sysUserService.findByName(username);
        if (sysUser == null) {//账号为空
            throw new UnknownAccountException("账号不存在");
        } else if (!password.equals(sysUser.getPassword())) {
            //密码不匹配
            throw new IncorrectCredentialsException("密码不正确");
        } else if (sysUser.getStatus() == 0) {
            //状态值为锁定状态
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());//放入shiro.调用CredentialsMatcher检验密码
    }

    /**
     * 授权(认证时调用)
     *
     * @param principal 当前账号
     * @return 认证信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principal) {
        SysUser sysUser = (SysUser) principal.getPrimaryPrincipal();
        //当前用户id
        Long userId = sysUser.getUserId();
        //根据id获得权限列表
        List<String> permissions = sysUserService.userPermsList(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perms : permissions) {//将空的权限去除
            if (StringUtils.isNotBlank(perms)) {
                permsSet.addAll(Arrays.asList(perms.trim().split(",")));
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);//将权限放入shiro中
        return info;
    }

}
