package com.wuhainan.service.impl;

import com.wuhainan.dao.EditNewsDao;
import com.wuhainan.entity.EditNews;
import com.wuhainan.service.EditNewsService;
import com.wuhainan.utils.BtsParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <br>Created by 吴海南 on 2017/6/5.
 * <br>星期一 at 11:36.
 */
@Service
public class EditNewsServiceImpl implements EditNewsService {

    @Resource
    private EditNewsDao dao;

    @Override
    public void saveNews(EditNews news) {
        dao.insert(news,true);
    }

    @Override
    public EditNews selectNewsById(Long newsId) {
        return dao.selectNewsById(newsId);
    }

    @Override
    public void editNews(EditNews news) {
        dao.updateById(news);
    }

    @Override
    public void deleteById(Long newsId) {
        dao.deleteById(newsId);
    }

    @Override
    public List<EditNews> newsList(BtsParams params) {
        return dao.newsList(params);
    }

    @Override
    public Long allCount(BtsParams params) {
        return dao.allCount(params);
    }
}
