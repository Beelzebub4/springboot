package com.wuhainan.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <br>Created by 吴海南 on 2017/6/4.
 * <br>星期日 at 13:48.
 */
public class FTPUtils {
    private FTPClient ftp;

    /**
     * @param addr     ip地址
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @throws IOException 异常
     */
    public FTPUtils(String addr, int port, String username, String password) throws IOException {
        ftp = new FTPClient();
        ftp.connect(addr, port);
        ftp.login(username, password);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        int reply;
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
        }
    }

    /**
     * @param path 上传到ftp服务器的路径
     * @throws IOException 异常
     */
    public void connect(String path) throws IOException {
        ftp.changeWorkingDirectory(path);
    }

    public boolean makeDirectory(String path) throws IOException {
        return ftp.makeDirectory(path);
    }

    public void upload(File file) throws Exception {
        FileInputStream input = new FileInputStream(file);
        boolean result = ftp.storeFile(file.getName(), input);
        input.close();
    }

    public void destroy() throws IOException {
        if (ftp != null) {
            ftp.disconnect();
            ftp = null;
        }
    }

    public static void main(String[] args) throws Exception {
        FTPUtils t = new FTPUtils("192.168.1.101", 2121, "ftp", "123456");
        boolean isDirectory = t.makeDirectory("1/123");
        t.connect("1/123");
        File file = new File("E:\\test\\test.txt");
        t.upload(file);
    }
}
