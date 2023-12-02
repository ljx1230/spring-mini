package com.ljx.springframework.context;

import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.factory.Aware;

/**
 * @Author: ljx
 * @Date: 2023/12/2 13:27
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
