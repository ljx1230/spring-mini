package com.ljx.springframework.beans.factory.config;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:39
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
    void addSingleton(String beanName,Object singletonObject);
}
