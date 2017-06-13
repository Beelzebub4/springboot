package com.wuhainan.controller;

import com.wuhainan.annotation.SysLogger;
import com.wuhainan.entity.MenuTypeDto;
import com.wuhainan.entity.SysMenu;
import com.wuhainan.service.SysMenuService;
import com.wuhainan.utils.BtsParams;
import com.wuhainan.utils.Constant;
import com.wuhainan.utils.PageUtils;
import com.wuhainan.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理控制层
 * <br>Created by Admin on 2017/5/8.
 * <br>星期一 at 20:57.
 */
@Controller
public class SysMenuController extends AbstractController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 菜单管理页面
     *
     * @return sysMenu
     */
    @RequestMapping("sysMenu")
    public R sysMenu(Model model) {
        //动态生成菜单类型radio
        List<MenuTypeDto> menuTypeList = new ArrayList<>();
        for (Constant.MenuType menuType : Constant.MenuType.values()) {
            MenuTypeDto menuTypeDto = new MenuTypeDto();
            menuTypeDto.setValue(menuType.getValue());
            menuTypeDto.setDescription(menuType.getDescription());
            menuTypeList.add(menuTypeDto);
        }
        model.addAttribute("menuTypeList", menuTypeList);
        return R.ok("sysMenu");
    }

    /**
     * 菜单分页列表
     *
     * @param params    分页参数
     * @param pageUtils 分页封装
     * @return 分页集合
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:list")
    @RequestMapping("menuPageList")
    public PageUtils menuPageList(BtsParams params, PageUtils pageUtils) {
        List<SysMenu> menuList = sysMenuService.menuPageList(params);
        Long total = sysMenuService.allCount(params);
        pageUtils.setTotal(total);
        pageUtils.setRows(menuList);
        return pageUtils;
    }

    /**
     * 用户拥有的菜单树
     *
     * @return 用户菜单集合
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:roleMenuTree")
    @RequestMapping("roleMenuTree")
    public R roleMenuTree() {
        return R.ok().put("roleMenuList", sysMenuService.roleMenuList(getUserId()));
    }

    /**
     * 不含按钮类型的菜单树
     *
     * @return menuList
     */
    @ResponseBody
    @RequiresPermissions("sys:menu:roleNbMenuTree")
    @RequestMapping("menuNoButtonTree")
    public R menuNoButtonTree() {
        List<SysMenu> menuList = sysMenuService.noButtonMenuList();
        SysMenu root = new SysMenu();
        root.setMenuId(0L);
        root.setName("根菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);
        return R.ok().put("menuList", menuList);
    }


    /**
     * 增加菜单
     *
     * @param sysMenu 菜单
     * @return void
     */
    @SysLogger("增加菜单")
    @ResponseBody
    @RequiresPermissions("sys:menu:add")
    @RequestMapping(value = "addMenu", method = RequestMethod.POST)
    public R addMenu(SysMenu sysMenu) {
        sysMenuService.addMenu(sysMenu);
        return R.ok("新增菜单" + sysMenu.getName() + "成功");
    }

    /**
     * 修改菜单
     *
     * @param sysMenu 菜单
     * @param menuId  菜单id
     * @return void
     */
    @SysLogger("修改菜单")
    @ResponseBody
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "editMenu/{menuId}", method = RequestMethod.POST)
    public R editMenu(SysMenu sysMenu, @PathVariable("menuId") Long menuId) {
        sysMenuService.editMenu(sysMenu);
        return R.ok("修改菜单" + sysMenu.getName() + "成功");
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     * @return Integer
     */
    @SysLogger("删除菜单")
    @ResponseBody
    @RequiresPermissions("sys:menu:del")
    @RequestMapping(value = "delMenu/{menuId}", method = RequestMethod.POST)
    public R delMenu(
            @PathVariable("menuId") Long menuId) {
        sysMenuService.deleteById(menuId);
        return R.ok("删除菜单成功");
    }

}
