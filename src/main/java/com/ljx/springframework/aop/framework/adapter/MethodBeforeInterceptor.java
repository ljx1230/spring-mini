package com.ljx.springframework.aop.framework.adapter;

import com.ljx.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: ljx
 * @Date: 2023/12/7 15:45
 */
public class MethodBeforeInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice advice;

    public MethodBeforeInterceptor() {
    }

    public void setAdvice(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    public MethodBeforeInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 执行被代理的方法之前，先执行before操作
        advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
