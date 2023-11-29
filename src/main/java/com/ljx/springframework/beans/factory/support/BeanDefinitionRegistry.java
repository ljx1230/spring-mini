package com.ljx.springframework.beans.factory.support;

import com.ljx.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:59
 * BeanDefinition注册表接口
 */
public interface BeanDefinitionRegistry {
    // 向注册表中注BeanDefinition
    void registerBeanDefinitionRegistry(String name, BeanDefinition beanDefinition);
}
