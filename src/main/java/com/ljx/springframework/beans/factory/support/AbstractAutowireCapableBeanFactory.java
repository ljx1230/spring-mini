package com.ljx.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.PropertyValue;
import com.ljx.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.ljx.springframework.beans.factory.config.BeanDefinition;
import com.ljx.springframework.beans.factory.config.BeanReference;


/**
 * @Author: ljx
 * @Date: 2023/11/29 19:01
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName,beanDefinition);
    }
    protected Object doCreateBean(String beanName,BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            // 实例化bean
            bean = createBeanInstance(beanDefinition);
            // 为bean注入属性
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean); // 加入容器中
        return bean;
    }

    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行前置处理器
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        //TODO 后面会在此处执行bean的初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        //执行BeanPostProcessor的后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        // TODO
        System.out.println("执行bean[" + beanName + "]的初始化方法");
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object res = existingBean;
        for(var processor : getBeanPostProcessorList()) {
            Object current = processor.postProcessBeforeInitialization(res,beanName);
            if(current == null) {
                return res;
            }
            res = current;
        }
        return res;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object res = existingBean;
        for(var processor : getBeanPostProcessorList()) {
            Object current = processor.postProcessAfterInitialization(res,beanName);
            if(current == null) {
                return res;
            }
            res = current;
        }
        return res;
    }

    // 实例化bean
    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    // 为bean注入属性
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition) {
        try {
            for(PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if(value instanceof BeanReference) {
                    // 如果注入的属性是一个Bean，则获取这个bean
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 通过set方法注入属性
//                Class<?> type = beanClass.getDeclaredField(name).getType();
//                String methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1); // 获取set方法名
//                Method method = beanClass.getDeclaredMethod(methodName, new Class[]{type});
//                method.invoke(bean,new Object[]{value});
                // 通过反射设置属性
                BeanUtil.setFieldValue(bean,name,value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values for bean: " + beanName, e);
        }
    }



}
