package com.ljx.springframework.beans.factory.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: ljx
 * @Date: 2023/11/29 19:32
 * 定义Bean的初始化策略
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
