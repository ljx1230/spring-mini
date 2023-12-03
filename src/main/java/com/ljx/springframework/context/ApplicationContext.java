package com.ljx.springframework.context;

import com.ljx.springframework.beans.factory.HierarchicalBeanFactory;
import com.ljx.springframework.beans.factory.ListableBeanFactory;
import com.ljx.springframework.core.io.ResourceLoader;

/**
 * 应用上下文
 * @Author: ljx
 * @Date: 2023/12/1 14:10
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
