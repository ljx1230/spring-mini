package com.ljx.springframework.context.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.ljx.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author: ljx
 * @Date: 2023/12/1 14:29
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    /**
     * 创建BeanFactory，加载BeanDefinition
     * @throws BeansException
     */
    @Override
    protected final void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载BeanDefinition
     * @param beanFactory
     * @throws BeansException
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
