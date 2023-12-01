package com.ljx.springframework.beans.factory;

/**
 * @Author: ljx
 * @Date: 2023/12/1 15:30
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
