package com.ljx.springframework.context.event;

import com.ljx.springframework.context.ApplicationContext;

/**
 * @Author: ljx
 * @Date: 2023/12/3 14:08
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
