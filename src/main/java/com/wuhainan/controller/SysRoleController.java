package com.wuhainan.controller;

import com.wuhainan.annotation.SysLogger;
import com.wuhainan.entity.SysRole;
import com.wuhainan.service.SysRoleService;
import com.wuhainan.utils.BtsParams;
import com.wuhainan.utils.Constant;
import com.wuhainan.utils.PageUtils;
import com.wuhainan.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理控制层
 * <br>Created by Admin on 2017/5/9.
 * <br>星期二 at 16:46.
 */
@Controller
public class SysRoleController extends AbstractController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 角色管理页面
     *
     * @return sysrole
     */
    @RequestMapping("sysRole")
    public R sysRole() {
        return R.ok("sysRole");
    }

    /**
     * 角色分页列表
     *
     * @param params    分页参数
     * @param pageUtils 分页封装
     * @return 角色分页集合
     */
    @ResponseBody
    @RequiresPermissions("sys:role:list")
    @RequestMapping("rolePageList")
    public PageUtils rolePageList(BtsParams params, PageUtils pageUtils) {
        List<SysRole> roleList = sysRoleService.rolePageList(params, getUserId());
        Long total = sysRoleService.allCount(params);
        pageUtils.setTotal(total);
        pageUtils.setRows(roleList);
        return pageUtils;
    }

    /**
     * 增加角色
     *
     * @param sysRole 角色
     * @return void
     */
    @SysLogger("增加角色")
    @ResponseBody
    @RequiresPermissions("sys:role:add")
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public R addRole(SysRole sysRole) {
        //创建者为当前用户
        sysRole.setCreateUserId(getUserId());
        sysRoleService.addRole(sysRole);
        return R.ok("增加角色" + sysRole.getRoleName() + "成功");
    }

    /**
     * 修改角色
     *
     * @param sysRole 角色
     * @param roleId  角色id
     * @return void
     */
    @SysLogger("修改角色")
    @ResponseBody
    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "editRole/{roleId}", method = RequestMethod.POST)
    public R editRole(SysRole sysRole, @PathVariable("roleId") Long roleId) {
        sysRole.setCreateUserId(sysRoleService.findById(roleId).getCreateUserId());
        //时间不变
        sysRole.setCreateTime(sysRoleService.findById(roleId).getCreateTime());
        sysRoleService.editRole(sysRole);
        return R.ok("修改角色" + sysRole.getRoleName() + "成功");
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return Integer
     */
    @SysLogger("删除角色")
    @ResponseBody
    @RequiresPermissions("sys:role:del")
    @RequestMapping(value = "delRole/{roleId}", method = RequestMethod.POST)
    public R delRole(
            @PathVariable("roleId") Long roleId) {
        if (roleId == Constant.SUPER_ROLE) {
            return R.error("不能删除管理员角色");
        } else {
            sysRoleService.deleteById(roleId);
            return R.ok("删除角色成功");
        }
    }
}
