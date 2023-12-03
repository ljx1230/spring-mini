package com.ljx.springframework.context.event;

import com.ljx.springframework.context.ApplicationContext;
import com.ljx.springframework.context.ApplicationEvent;

/**
 * @Author: ljx
 * @Date: 2023/12/3 14:09
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
