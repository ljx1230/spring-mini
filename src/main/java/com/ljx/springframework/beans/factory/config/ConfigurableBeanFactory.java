package com.ljx.springframework.beans.factory.config;

import com.ljx.springframework.beans.factory.BeanFactory;
import com.ljx.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @Author: ljx
 * @Date: 2023/12/1 13:08
 * 可自行配置的BeanFactory
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {
    /**
     * 添加Bean的后处理器
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例Bean
     */
    void destroySingletons();

}
