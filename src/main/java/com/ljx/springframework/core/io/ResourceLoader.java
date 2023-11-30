package com.ljx.springframework.core.io;

/**
 * @Author: ljx
 * @Date: 2023/11/30 18:03
 * 资源加载器接口
 */
public interface ResourceLoader {
    Resource getResource(String location);
}
