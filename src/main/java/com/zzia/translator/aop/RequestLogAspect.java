package com.zzia.translator.aop;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestLogAspect {

    private Logger logger = Logger.getLogger(RequestLogAspect.class.getName());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println(request.getRemoteAddr());
        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }
    
    public void doAfter (JoinPoint joinPoint) throws Throwable {
    	System.out.println("log Ending method: "
                + joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName());
    	throw new RuntimeException("abc");
    }
    
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
    
    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("method " + jp.getTarget().getClass().getName()
                + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
    }


}

