package com.ljx.springframework.aop;

/**
 * @Author: ljx
 * @Date: 2023/12/7 14:12
 * 被代理的目标对象
 */
public class TargetSource {
    private final Object target;
    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return target;
    }
}
