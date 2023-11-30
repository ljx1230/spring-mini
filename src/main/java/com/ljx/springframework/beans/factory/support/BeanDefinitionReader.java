package com.ljx.springframework.beans.factory.support;


import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.core.io.Resource;
import com.ljx.springframework.core.io.ResourceLoader;

/**
 * @Author: ljx
 * @Date: 2023/11/30 19:20
 * 读取Bean定义信息的接口（读取BeanDefinition）
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws  BeansException;
}
