package com.ljx.springframework.beans.factory.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.BeanFactory;
import com.ljx.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:48
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if(bean != null) { // 如果容器中已经存在,直接返回bean
            return bean;
        }
        // 容器中不存在,创建Bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition);
    }
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeansException;
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
