package com.ljx.springframework.beans.factory.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.config.BeanDefinition;
import com.ljx.springframework.beans.factory.config.BeanPostProcessor;
import com.ljx.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:48
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
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

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }
}
