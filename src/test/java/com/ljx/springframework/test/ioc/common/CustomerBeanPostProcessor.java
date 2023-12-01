package com.ljx.springframework.test.ioc.common;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.config.BeanPostProcessor;
import com.ljx.springframework.test.ioc.bean.Car;

/**
 * @Author: ljx
 * @Date: 2023/12/1 13:43
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessBeforeInitialization");
        if("car".equals(beanName)) {
            ((Car) bean).setBrand("兰博基尼");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization");
        return bean;
    }
}
