package com.ljx.springframework.context.event;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.BeanFactory;
import com.ljx.springframework.beans.factory.BeanFactoryAware;
import com.ljx.springframework.context.ApplicationEvent;
import com.ljx.springframework.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ljx
 * @Date: 2023/12/3 14:03
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();
    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
