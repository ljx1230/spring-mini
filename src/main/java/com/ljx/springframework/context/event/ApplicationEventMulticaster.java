package com.ljx.springframework.context.event;

import com.ljx.springframework.context.ApplicationEvent;
import com.ljx.springframework.context.ApplicationListener;

/**
 * @Author: ljx
 * @Date: 2023/12/3 14:01
 */
public interface ApplicationEventMulticaster {

    // 添加监听器
    void addApplicationListener(ApplicationListener<?> listener);

    // 删除监听器
    void removeApplicationListener(ApplicationListener<?> listener);

    // 消息多播器
    void multicastEvent(ApplicationEvent event);
}
