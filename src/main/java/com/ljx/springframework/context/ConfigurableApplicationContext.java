package com.ljx.springframework.context;

import com.ljx.springframework.beans.BeansException;

/**
 * @Author: ljx
 * @Date: 2023/12/1 14:13
 */
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
