package com.ljx.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Author: ljx
 * @Date: 2023/12/7 14:15
 */
public class AdvisedSupport {
    private TargetSource targetSource;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    private MethodMatcher methodMatcher;
    private MethodInterceptor methodInterceptor;

}
