package com.ljx.springframework.context;

/**
 * 发布事件接口
 * @Author: ljx
 * @Date: 2023/12/3 13:51
 */
public interface ApplicationEventPublisher {
    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
