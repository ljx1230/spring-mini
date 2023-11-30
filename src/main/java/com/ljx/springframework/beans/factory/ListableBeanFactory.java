package com.ljx.springframework.beans.factory;

import com.ljx.springframework.beans.BeansException;

import java.util.Map;

/**
 * @Author: ljx
 * @Date: 2023/11/30 23:00
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 返回指定类型的所有bean实例
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回所有定义的Bean的名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
