package com.ljx.springframework.context.support;

import com.ljx.springframework.beans.BeansException;

/**
 * @Author: ljx
 * @Date: 2023/12/1 14:36
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;

    /**
     * 从xml文件加载BeanDefinition，并且刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
