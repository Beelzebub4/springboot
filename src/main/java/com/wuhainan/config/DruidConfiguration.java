package com.wuhainan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * druid监控配置
 * <br>Created by Admin on 2017/5/17.
 * <br>星期三 at 17:01.
 */
@Configuration
public class DruidConfiguration {

    @Resource
    private DruidProperty druidProperty;

    @Bean(name = "datasource")//数据库配置bean
    public DataSource getDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidProperty.getDriverClassName());
        dataSource.setUrl(druidProperty.getUrl());
        dataSource.setUsername(druidProperty.getUsername());
        dataSource.setPassword(druidProperty.getPassword());
        dataSource.setFilters(druidProperty.getFilter());
        dataSource.setInitialSize(druidProperty.getInitialSize());
        dataSource.setMinIdle(druidProperty.getMinIdle());
        dataSource.setMaxActive(druidProperty.getMaxActive());
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean DruidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(
                        new StatViewServlet(), "/druid/*");
        //白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        ////ip黑名单
        //servletRegistrationBean.addInitParameter("deny", "192.168.20.161");
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "druid");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean =
                new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.html,*.md,*.ico,/druid2/*");
        return filterRegistrationBean;
    }

}
