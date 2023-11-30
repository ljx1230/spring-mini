package com.ljx.springframework.beans.factory;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: ljx
 * @Date: 2023/11/30 23:02
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory{
    /**
     * 根据名称查找BeanDefinition
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例对象
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
