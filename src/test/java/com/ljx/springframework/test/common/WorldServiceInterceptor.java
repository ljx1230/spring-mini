package com.ljx.springframework.test.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: ljx
 * @Date: 2023/12/7 14:30
 */
public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("前置增强");
        Object result = methodInvocation.proceed();
        System.out.println("后置增强");
        return result;
    }
}
