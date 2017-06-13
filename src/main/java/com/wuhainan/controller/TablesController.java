package com.wuhainan.controller;

import com.wuhainan.annotation.SysLogger;
import com.wuhainan.config.GenConf;
import com.wuhainan.entity.Tables;
import com.wuhainan.service.TablesService;
import com.wuhainan.utils.BtsParams;
import com.wuhainan.utils.PageUtils;
import com.wuhainan.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.GenFilter;
import org.beetl.sql.ext.gen.MapperCodeGen;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据库表视图层
 * <br>Created by Admin on 2017/5/2.
 * <br>星期二 at 9:30.
 */
@Controller
public class TablesController {

    @Resource
    private TablesService tablesService;

    @Resource
    private GenConf genConf;//路径配置

    /**
     * 主页面
     *
     * @return 返回tables页面
     */
    @RequestMapping("tables")
    public R tables() {
        return R.ok("tables");
    }

    /**
     * 数据库表格分页查询列表
     *
     * @param params    参数
     * @param pageUtils 分页封装
     * @return pageUtils
     */
    @ResponseBody
    @RequiresPermissions("sys:table:list")
    @RequestMapping("tablePageList")
    public PageUtils tablePageList(BtsParams params, PageUtils pageUtils) {
        List<Tables> tablesList = tablesService.tablePageList(params);
        Long total = tablesService.allCount(params);
        pageUtils.setRows(tablesList);
        pageUtils.setTotal(total);
        return pageUtils;
    }

    /**
     * 单表的自动生成代码
     *
     * @param tableName 表名
     * @return 返回tables页面
     * @throws Exception 抛出异常
     */
    @SysLogger(value = "生成代码")
    @ResponseBody
    @RequiresPermissions("sys:table:genOne")
    @RequestMapping(value = "genCode", method = RequestMethod.POST)
    public R genCode(String tableName) throws Exception {
        GenConfig config = new GenConfig();
        MapperCodeGen mapperCodeGen = new MapperCodeGen(genConf.getDaoPkg());
        config.codeGens.add(mapperCodeGen);
        tablesService.getSQLManager().genPojoCode(//生成实体类代码
                tableName, genConf.getEntityPkg(), config);
        tablesService.getSQLManager().genSQLFile(tableName);
        return R.ok();
    }

    /**
     * 生成所有代码 idea使用外部tomcat时
     * 默认输出到tomcat的bin文件夹下
     *
     * @return 返回tables页面
     * @throws Exception 抛出异常
     */
    @RequiresPermissions("sys:table:genAll")
    @RequestMapping(value = "genAllCode")
    public String genAllCode() throws Exception {
        GenConfig config = new GenConfig();
        MapperCodeGen mapperCodeGen = new MapperCodeGen(genConf.getDaoPkg());
        config.codeGens.add(mapperCodeGen);
        tablesService.getSQLManager().genALL(genConf.getEntityPkg(), config, new GenFilter());
        return "tables";
    }

}