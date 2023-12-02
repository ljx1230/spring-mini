package com.ljx.springframework.beans.factory.support;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.FactoryBean;
import com.ljx.springframework.beans.factory.config.BeanDefinition;
import com.ljx.springframework.beans.factory.config.BeanPostProcessor;
import com.ljx.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ljx
 * @Date: 2023/11/29 18:48
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String name) throws BeansException {

        Object sharedInstance = getSingleton(name);
        if(sharedInstance != null) { // 如果容器中已经存在直接返回
            return getObjectForBeanInstance(sharedInstance, name);
        }
        // 否则创建bean然后返回
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name,beanDefinition);
        return getObjectForBeanInstance(bean,name);
    }

    /**
     * 如果是FactoryBean，从FactoryBean#getObject中创建bean
     * @param sharedInstance
     * @param name
     * @return
     */
    protected Object getObjectForBeanInstance(Object sharedInstance, String name) {
        if(sharedInstance instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) sharedInstance;
            try {
                if(factoryBean.isSingleton()) {
                    //singleton作用域bean，从缓存中获取
                    sharedInstance = this.factoryBeanObjectCache.get("name");
                    if(sharedInstance == null) {
                        sharedInstance = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(name,sharedInstance);
                    } else {
                        //prototype作用域bean，新创建bean
                        sharedInstance = factoryBean.getObject();
                    }
                }
            } catch (Exception e) {
                throw new BeansException("FactoryBean threw exception on object[" + name + "] creation", e);
            }
        }
        return sharedInstance;
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

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return ((T) getBean(name));
    }
}
