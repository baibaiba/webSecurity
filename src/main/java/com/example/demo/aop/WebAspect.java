package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;


@Aspect
@Component
@Order(-5)
public class WebAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.example.demo.controller.*)")
    public void webLog() {
    }

    @Around("webLog()")
    public void doRound(ProceedingJoinPoint joinPoint) {
        logger.info("WebLogAspect.doRound()");
        startTime.set(System.currentTimeMillis());
        // 接收到的请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }

        // 可以组装acc日志
        Object proceed;
        try {
            // 调用目标方法
            proceed = joinPoint.proceed();
            // 回填acc日志响应字段
        } catch (Throwable throwable) {
            // 记录异常
        }

        // 处理完请求，返回内容
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
    }

//    /**
//     * @param joinPoint
//     * @Before在切入点开始处切入内容
//     */
//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) {
//        startTime.set(System.currentTimeMillis());
//        // 接收到的请求，记录请求内容logger.info("WebLogAspect.doBefore()");
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//
//        //获取所有参数方法一：
//        Enumeration<String> enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String paraName = (String) enu.nextElement();
//            System.out.println(paraName + ": " + request.getParameter(paraName));
//        }
//    }
//
//    @After("webLog()")
//    public void  doAfter(JoinPoint joinPoint){
//        // 处理完请求，未返回内容
//        logger.info("WebLogAspect.doAfter()");
//    }
//
//    /**
//     * @AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
//     * @param joinPoint
//     */
//    @AfterReturning("webLog()")
//    public void  doAfterReturning(JoinPoint joinPoint){
//        // 处理完请求，返回内容
//        logger.info("WebLogAspect.doAfterReturning()");
//        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
//    }
}
