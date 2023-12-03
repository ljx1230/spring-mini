package com.ljx.springframework.test.common.event;

import com.ljx.springframework.context.ApplicationContext;
import com.ljx.springframework.context.event.ApplicationContextEvent;

/**
 * @Author: ljx
 * @Date: 2023/12/3 14:59
 */
public class CustomEvent extends ApplicationContextEvent {

    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
