package com.wuhainan.aop;

import com.alibaba.fastjson.JSON;
import com.wuhainan.annotation.SysLogger;
import com.wuhainan.entity.SysLog;
import com.wuhainan.service.SysLogService;
import com.wuhainan.utils.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志切面处理类
 * <br>Created by Admin on 2017/5/5.
 * <br>星期五 at 16:32.
 */
@Aspect
@Component
public class SysLogAspect {
    @Resource
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.wuhainan.annotation.SysLogger)")
    public void logPointcut() {//切入点为SysLog注解

    }

    @Before("logPointcut()")
    public void saveSysLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        if (sysLogger != null) {
            //设置用户操作的值(注解上的描述)
            sysLog.setOperation(sysLogger.value());
        }

        //请求的方法名称
        String className = joinPoint.getTarget().getClass().getName();//类名
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        sysLog.setParams(params);

        //设置ip地址
        String ip = ShiroUtils.getSession().getHost();
        sysLog.setIp(ip);

        //设置用户名
        String username = ShiroUtils.getSysUser().getUsername();
        sysLog.setUsername(username);

        //设置日志时间
        sysLog.setCreateDate(new Date());

        //保存系统日志
        sysLogService.save(sysLog);
    }
}
