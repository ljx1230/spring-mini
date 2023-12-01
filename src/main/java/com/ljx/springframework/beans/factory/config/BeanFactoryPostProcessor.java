package com.ljx.springframework.beans.factory.config;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Author: ljx
 * @Date: 2023/12/1 13:05
 * 可以自定义修改BeanDefinition
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有BeanDefinition加载完成后，但在bean实例化之前，提供修改BeanDefinition属性值的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
