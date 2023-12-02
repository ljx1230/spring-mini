package com.ljx.springframework.beans.factory;

/**
 * @Author: ljx
 * @Date: 2023/12/2 15:48
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;
    boolean isSingleton();
}
