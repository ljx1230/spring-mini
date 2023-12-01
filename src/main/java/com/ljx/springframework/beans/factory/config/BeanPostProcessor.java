package com.ljx.springframework.beans.factory.config;

import com.ljx.springframework.beans.BeansException;

/**
 * @Author: ljx
 * @Date: 2023/12/1 13:01
 * 用于修改实例化后的bean
 */
public interface BeanPostProcessor {
    /**
     * bean执行初始化方法之前执行该方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException;

    /**
     * bean执行初始化方法之后执行该方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException;

}
