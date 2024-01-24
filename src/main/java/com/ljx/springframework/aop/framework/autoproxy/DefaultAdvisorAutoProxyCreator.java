package com.ljx.springframework.aop.framework.autoproxy;

import com.ljx.springframework.aop.*;
import com.ljx.springframework.aop.aspectj.AspectJExpressionPointCutAdvisor;
import com.ljx.springframework.aop.framework.ProxyFactory;
import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.BeanFactory;
import com.ljx.springframework.beans.factory.BeanFactoryAware;
import com.ljx.springframework.beans.factory.config.BeanDefinition;
import com.ljx.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.ljx.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Author: ljx
 * @Date: 2023/12/11 17:11
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || PointCut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(isInfrastructureClass(beanClass)) {
            return null;
        }

        var advisors = beanFactory.getBeansOfType(AspectJExpressionPointCutAdvisor.class).values();
        try {
            for(var advisor : advisors) {
                ClassFilter classFilter = advisor.getPointCut().getClassFilter();
                if(classFilter.matches(beanClass)) {
                    AdvisedSupport advisedSupport = new AdvisedSupport();

                    BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                    Object bean = beanFactory.getInstantiationStrategy().instantiate(beanDefinition);
                    TargetSource targetSource = new TargetSource(bean);
                    advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                    advisedSupport.setMethodMatcher(advisor.getPointCut().getMethodMatcher());

                    // 返回代理对象
                    return new ProxyFactory(advisedSupport).getProxy();
                }
            }

        } catch (Exception e) {
            throw new BeansException("Error create proxy bean for: " + beanName, e);
        }
        return null;
    }
}
