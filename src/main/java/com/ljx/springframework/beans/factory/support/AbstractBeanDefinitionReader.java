package com.ljx.springframework.beans.factory.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.core.io.DefaultResourceLoader;
import com.ljx.springframework.core.io.ResourceLoader;

/**
 * @Author: ljx
 * @Date: 2023/11/30 19:25
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
        this.resourceLoader = new DefaultResourceLoader();
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for(var location : locations) {
            loadBeanDefinitions(location);
        }
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
