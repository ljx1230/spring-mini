package com.ljx.springframework.test.ioc.common;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.PropertyValue;
import com.ljx.springframework.beans.PropertyValues;
import com.ljx.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.ljx.springframework.beans.factory.config.BeanDefinition;
import com.ljx.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Author: ljx
 * @Date: 2023/12/1 13:38
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","xjy"));
    }
}
