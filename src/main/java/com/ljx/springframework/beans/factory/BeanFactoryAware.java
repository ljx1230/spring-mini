package com.ljx.springframework.beans.factory;

import com.ljx.springframework.beans.BeansException;

/**
 * @Author: ljx
 * @Date: 2023/12/2 13:26
 *  实现该接口后，能感知所属BeanFactory
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
