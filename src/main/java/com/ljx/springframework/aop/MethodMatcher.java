package com.ljx.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author: ljx
 * @Date: 2023/12/3 15:56
 */
public interface MethodMatcher {
    boolean matches(Method method,Class<?> targetClass);
}
