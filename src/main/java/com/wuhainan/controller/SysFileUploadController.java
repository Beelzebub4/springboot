package com.wuhainan.controller;

import com.wuhainan.entity.SysFileUpload;
import com.wuhainan.service.SysFileUploadService;
import com.wuhainan.utils.FTPUtils;
import com.wuhainan.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * <br>Created by 吴海南 on 2017/6/2.
 * <br>星期五 at 11:30.
 */
@Controller
public class SysFileUploadController {

    @Resource
    private SysFileUploadService sysFileUploadService;

    /**
     * 文件上传页面
     *
     * @return sysFileUpload
     */
    @RequestMapping("sysFileUpload")
    public R sysFileUpload() {
        return R.ok("sysFileUpload");
    }

    @ResponseBody
    @RequestMapping("fileUpload/{type}")
    public R fileUpload(@RequestParam("file") List<MultipartFile> files,
                        @PathVariable("type") Integer type) throws Exception {
        String addr = "192.168.20.24";
        Integer port = 2121;
        String username = "ftp";
        String password = "123456";
        FTPUtils ftp = null;
        ftp = new FTPUtils(addr, port, username, password);
        SysFileUpload fileUpload = new SysFileUpload();
        if (files.size()==0) {
            return R.error("文件不能为空");
        }
        for (int i=0;i<files.size();i++){
            fileUpload.setFileName(files.get(i).getOriginalFilename());
            String suffixName = fileUpload.getFileName().substring(
                    fileUpload.getFileName().lastIndexOf("."));
            fileUpload.setFileName(i+suffixName);
            fileUpload.setCreateDate(new Date());
            fileUpload.setFileUrl("E://test//" + type + "//");
            File image=new File(fileUpload.getFileUrl() + fileUpload.getFileName());
            if (!image.getParentFile().exists()) {
                image.getParentFile().mkdirs();
            }
            files.get(i).transferTo(image);
            ftp.makeDirectory("1/" + type);
            ftp.connect("1/" + type);
            ftp.upload(image);
            image.delete();
            fileUpload.setFileUrl("FTP://" + addr + ":" + port + "/1/" + type + "/" + fileUpload.getFileName());
            sysFileUploadService.saveFile(fileUpload);
        }
        return R.ok();
    }
}
