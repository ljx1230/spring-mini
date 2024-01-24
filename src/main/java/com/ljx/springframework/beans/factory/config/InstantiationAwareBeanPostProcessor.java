package com.ljx.springframework.beans.factory.config;

import com.ljx.springframework.beans.BeansException;

/**
 * @Author: ljx
 * @Date: 2023/12/11 17:10
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInstantiation(Class<?> beanClass,String beanName) throws BeansException;
}
