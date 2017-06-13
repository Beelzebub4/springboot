package com.wuhainan.controller;

import com.wuhainan.annotation.SysLogger;
import com.wuhainan.entity.EditNews;
import com.wuhainan.service.EditNewsService;
import com.wuhainan.utils.BtsParams;
import com.wuhainan.utils.PageUtils;
import com.wuhainan.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * <br>Created by 吴海南 on 2017/6/5.
 * <br>星期一 at 11:40.
 */
@Controller
public class EditNewsController {
    @Resource
    private EditNewsService editNewsService;

    private String newsContent;
    private String title;
    /**
     * 新闻编辑页面
     *
     * @return newsEdit
     */
    @RequestMapping("newsEdit")
    public R sysFileUpload() {
        return R.ok("newsEdit");
    }

    @ResponseBody
    @RequestMapping("saveNews")
    public R saveNews(@RequestBody String content) {
        EditNews news = new EditNews();

        try {
            newsContent = URLDecoder.decode(content, "UTF-8");
            title = newsContent.substring(newsContent.indexOf(">") + 1,
                    newsContent.lastIndexOf("</h"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if ("".equals(title)){
            return R.error("标题不能为空");
        }
        news.setContent(newsContent);
        news.setTitle(title);
        news.setCreateDate(new Date());
        editNewsService.saveNews(news);
        return R.ok();
    }
    @ResponseBody
    @RequestMapping("newsPageList")
    public PageUtils tablePageList(BtsParams params, PageUtils pageUtils) {
        List<EditNews> newsList = editNewsService.newsList(params);
        Long total = editNewsService.allCount(params);
        pageUtils.setRows(newsList);
        pageUtils.setTotal(total);
        return pageUtils;
    }
    @SysLogger("修改新闻")
    @ResponseBody
    @RequestMapping(value = "editNews/{newsId}", method = RequestMethod.POST)
    public R editNews(@RequestBody String content, @PathVariable("newsId") Long newsId) {
        try {
            newsContent = URLDecoder.decode(content, "UTF-8");
            title = newsContent.substring(newsContent.indexOf(">") + 1,
                    newsContent.lastIndexOf("</h"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        EditNews news = editNewsService.selectNewsById(newsId);
        news.setCreateDate(new Date());
        news.setContent(newsContent);
        news.setTitle(title);
        editNewsService.editNews(news);
        return R.ok("修改菜单" + news.getTitle() + "成功");
    }
    @SysLogger("删除新闻")
    @ResponseBody
    @RequestMapping(value = "delNews/{newsId}", method = RequestMethod.POST)
    public R delNews(
            @PathVariable("newsId") Long newsId) {
        editNewsService.deleteById(newsId);
        return R.ok("删除新闻成功");
    }
}
