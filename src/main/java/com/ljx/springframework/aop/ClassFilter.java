package com.ljx.springframework.aop;

/**
 * @Author: ljx
 * @Date: 2023/12/3 15:56
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
