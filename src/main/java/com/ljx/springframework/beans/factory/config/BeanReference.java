package com.ljx.springframework.beans.factory.config;

/**
 * @Author: ljx
 * @Date: 2023/11/30 14:28
 * 一个Bean对另一个Bean的引用
 */
public class BeanReference {
    private final String beanName;
    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
    public String getBeanName() {
        return beanName;
    }
}
