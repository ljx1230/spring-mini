package com.ljx.springframework.beans.factory.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:59
 * BeanDefinition注册表接口
 */
public interface BeanDefinitionRegistry {
    // 向注册表中注BeanDefinition
    void registerBeanDefinitionRegistry(String name, BeanDefinition beanDefinition);

    /**
     * 根据名称获取BeanDefinition
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition实例
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回所有定义了的bean的名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
