package com.ljx.springframework.aop;

/**
 * @Author: ljx
 * @Date: 2023/12/3 15:57
 * 切点
 */
public interface PointCut {
    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();
}
