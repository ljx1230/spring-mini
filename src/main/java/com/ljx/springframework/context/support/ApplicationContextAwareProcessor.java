package com.ljx.springframework.context.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.config.BeanPostProcessor;
import com.ljx.springframework.context.ApplicationContext;
import com.ljx.springframework.context.ApplicationContextAware;

/**
 * @Author: ljx
 * @Date: 2023/12/2 13:36
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;
    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
