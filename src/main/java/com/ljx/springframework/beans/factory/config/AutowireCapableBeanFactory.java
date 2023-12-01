package com.ljx.springframework.beans.factory.config;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.BeanFactory;

/**
 * @Author: ljx
 * @Date: 2023/12/1 13:11
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行BeanPostProcessors的postProcessBeforeInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;
    /**
     * 执行BeanPostProcessors的postProcessAfterInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
