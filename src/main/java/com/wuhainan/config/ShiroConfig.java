package com.wuhainan.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.wuhainan.utils.AuthRealm;
import com.wuhainan.utils.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * shiro配置类
 * <br>Created by Admin on 2017/5/4.
 * <br>星期四 at 19:31.
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean =
                new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<>();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("login");
        filters.put("logout", logoutFilter);
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChain = new LinkedHashMap<>();//过滤链
        filterChain.put("/mysql/**", "anon");
        filterChain.put("/bootstrap-3/**", "anon");
        filterChain.put("/css/**", "anon");
        filterChain.put("/js/**", "anon");
        filterChain.put("/ztree/**", "anon");
        filterChain.put("/logout", "logout");
        filterChain.put("/login", "anon");
        filterChain.put("/doLogin", "anon");
        filterChain.put("/captcha.jpg", "anon");
        filterChain.put("/**", "authc");//无需认证访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(
                filterChain);
        shiroFilterFactoryBean.setLoginUrl("/login");//设置登录页面 默认为/login
        shiroFilterFactoryBean.setSuccessUrl("logon");//登录成功页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");//权限不足页面
        return shiroFilterFactoryBean;
    }

    @Bean(name = "credentialsMatcher")//自定义密码比较
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    @Bean(name = "producer")
    public DefaultKaptcha kaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        properties.setProperty("kaptcha.image.width", "135");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }

    @Bean(name = "authRealm")//自定义权限登录验证
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthRealm authRealm(
            @Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher) {
        AuthRealm realm = new AuthRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean(name = "ehCacheManager")//缓存
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        return new EhCacheManager();
    }

    @Bean(name = "securityManager")//核心安全事务管理
    public SecurityManager securityManager(
            @Qualifier("authRealm") AuthRealm authRealm,//自定义验证器
            @Qualifier("ehCacheManager") EhCacheManager ehCacheManager//缓存
    ) {
        DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setCacheManager(ehCacheManager);
        return securityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator =
                new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean//授权认证
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
