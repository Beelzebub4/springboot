package com.wuhainan.service.impl;

import com.wuhainan.dao.SysFileUploadDao;
import com.wuhainan.entity.SysFileUpload;
import com.wuhainan.service.SysFileUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <br>Created by 吴海南 on 2017/6/2.
 * <br>星期五 at 11:21.
 */
@Service
public class SysFileUploadServiceImpl implements SysFileUploadService {

    @Resource
    private SysFileUploadDao dao;
    @Override
    public void saveFile(SysFileUpload fileUpload) {
        dao.insert(fileUpload,true);
    }
}
