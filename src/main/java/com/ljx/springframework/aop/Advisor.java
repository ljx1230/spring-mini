package com.ljx.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @Author: ljx
 * @Date: 2023/12/7 16:03
 */
public interface Advisor {
    Advice getAdvice();
}
