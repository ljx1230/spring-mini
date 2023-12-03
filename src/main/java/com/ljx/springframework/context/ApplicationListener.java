package com.ljx.springframework.context;

import java.util.EventListener;

/**
 * @Author: ljx
 * @Date: 2023/12/3 13:54
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
