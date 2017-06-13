package com.wuhainan;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 启动类放在根目录下
 * 部署war包启动方式 通过继承
 * SpringBootServletInitializer
 */
@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        application.bannerMode(Banner.Mode.OFF);//关闭启动标语
        //(Class... sources)
        return application.sources(SpringbootApplication.class);
    }

    //改jar包部署时main方法启动
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(SpringbootApplication.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }

}
