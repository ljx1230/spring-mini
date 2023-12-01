package com.ljx.springframework.beans.factory;

/**
 * @Author: ljx
 * @Date: 2023/12/1 15:28
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
