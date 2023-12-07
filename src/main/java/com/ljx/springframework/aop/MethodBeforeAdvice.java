package com.ljx.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author: ljx
 * @Date: 2023/12/7 15:43
 */
public interface MethodBeforeAdvice extends BeforeAdvice{
    void before(Method method,Object[] args,Object target) throws Throwable;
}
