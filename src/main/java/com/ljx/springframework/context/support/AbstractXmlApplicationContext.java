package com.ljx.springframework.context.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.ljx.springframework.beans.factory.xml.XmlFileBeanDefinitionReader;

/**
 * @Author: ljx
 * @Date: 2023/12/1 14:34
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlFileBeanDefinitionReader beanDefinitionReader = new XmlFileBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if(configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
