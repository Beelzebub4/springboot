package com.wuhainan.controller;

import com.wuhainan.annotation.SysLogger;
import com.wuhainan.entity.SysUser;
import com.wuhainan.service.SysRoleService;
import com.wuhainan.service.SysUserService;
import com.wuhainan.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理控制层
 * <br>Created by Admin on 2017/5/4.
 * <br>星期四 at 9:27.
 */
@Controller
public class SysUserController extends AbstractController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 用户管理页面
     *
     * @param model 前后台传值model
     * @return sysuser
     */
    @RequestMapping("sysUser")
    public R users(Model model) {
        model.addAttribute("roleList", sysRoleService.roleList(getUserId()));
        return R.ok("sysUser");
    }

    /**
     * 用户管理分页列表
     * 需要拥有权限 sys:user:list
     *
     * @param params    分页参数
     * @param pageUtils 分页封装
     * @return 用户分页集合
     */
    @ResponseBody
    @RequiresPermissions("sys:user:list")
    @RequestMapping("userPageList")
    public PageUtils userList(BtsParams params, PageUtils pageUtils) {
        List<SysUser> userList = sysUserService.userPageList(params);
        Long total = sysUserService.allCount(params);
        pageUtils.setTotal(total);
        pageUtils.setRows(userList);
        return pageUtils;
    }

    /**
     * 增加用户
     *
     * @param sysUser 用户
     * @return void
     */
    @SysLogger("增加用户")
    @ResponseBody
    @RequiresPermissions("sys:user:add")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public R addUser(SysUser sysUser) {
        //创建者为当前用户
        R result = ajaxExistUser(sysUser.getUsername());
        if (!R.error("false").equals(result)) {
            sysUser.setCreateUserId(getUserId());
            sysUserService.addUser(sysUser);
            return R.ok("增加用户" + sysUser.getUsername() + "成功");
        } else {
            return R.error("用户名已存在");
        }
    }

    /**
     * 修改用户
     *
     * @param sysUser 用户
     * @param userId  用户id
     * @return void
     */
    @SysLogger("修改用户")
    @ResponseBody
    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "editUser/{userId}", method = RequestMethod.POST)
    public R editUser(SysUser sysUser, @PathVariable("userId") Long userId) {
        //修改者为当前用户
        sysUser.setCreateUserId(getUserId());
        //这里设置创建时间为初始创建时间 修改不改变
        sysUser.setCreateTime(sysUserService.findById(userId).getCreateTime());
        sysUserService.editUser(sysUser);
        return R.ok("修改用户" + sysUser.getUsername() + "成功");
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return Integer
     */
    @SysLogger("删除用户")
    @ResponseBody
    @RequiresPermissions("sys:user:del")
    @RequestMapping(value = "delUser/{userId}", method = RequestMethod.POST)
    public R delUser(
            @PathVariable("userId") Long userId) {
        if (userId == Constant.SUPER_ADMIN) {
            return R.error("不能删除系统管理员");
        } else if (userId.equals(getUserId())) {
            return R.error("当前用户无法删除");
        } else {
            sysUserService.deleteById(userId);
            return R.ok("删除用户成功");
        }
    }

    /**
     * 添加或修改用户时ajax验证账号是否存在
     *
     * @param usernameAjax 实时用户名
     * @return 账号是否存在的信息
     */
    @ResponseBody
    @RequestMapping("ajaxExistUser/{usernameAjax}")
    public R ajaxExistUser(@PathVariable("usernameAjax") String usernameAjax) {
        if (usernameAjax.equals(sysUserService.ajaxExistUser(usernameAjax))) {
            return R.error("false");//用户名存在
        } else {
            return R.ok("true");
        }
    }

    /**
     * 根据用户id查询用户名
     * 用于ajax修改 用户名比较
     *
     * @param userId 用户id
     * @return 用户名
     */
    @ResponseBody
    @RequestMapping("findUserById/{userId}")
    public R findUserById(@PathVariable("userId") Long userId) {
        return R.ok(sysUserService.findById(userId).getUsername());
    }

    /**
     * 修改当前用户密码
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return void
     */
    @SysLogger("修改密码")
    @ResponseBody
    @RequestMapping("updatePassword")
    public R password(String oldPassword, String newPassword) {
        //sha256加密
        oldPassword = new Sha256Hash(oldPassword).toHex();
        newPassword = new Sha256Hash(newPassword).toHex();
        //更新密码
        Integer count = sysUserService.updatePassword(getUserId(), oldPassword, newPassword);
        if (count == 0) {
            return R.error("原密码不正确");
        }
        //退出
        ShiroUtils.logout();
        return R.ok("login");
    }
}
