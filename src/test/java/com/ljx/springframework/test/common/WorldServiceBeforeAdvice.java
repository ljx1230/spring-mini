package com.ljx.springframework.test.common;

import com.ljx.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: ljx
 * @Date: 2023/12/7 15:49
 */
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("前置通知");
    }
}
