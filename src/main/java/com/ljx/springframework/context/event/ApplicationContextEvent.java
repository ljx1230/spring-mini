package com.ljx.springframework.context.event;

import com.ljx.springframework.context.ApplicationContext;
import com.ljx.springframework.context.ApplicationEvent;

/**
 * @Author: ljx
 * @Date: 2023/12/3 13:57
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
